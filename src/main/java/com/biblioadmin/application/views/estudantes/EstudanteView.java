package com.biblioadmin.application.views.estudantes;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.service.EstudantesService;
import com.biblioadmin.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;
import java.io.*;
import java.util.function.Consumer;

@PageTitle("Estudantes")
@Route(value = "estudantes", layout = MainLayout.class)
@RouteAlias(value = "none", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class EstudanteView extends VerticalLayout {
    public EstudanteView(EstudantesService service) {
        Grid<Estudante> grid = new Grid<>();

        final GridListDataView<Estudante> gridListDataView = grid.setItems(service.listarTodos());
        grid.addColumn(Estudante::getId).setHeader("ID").setWidth("60px");
        grid.addColumn(Estudante::getNome).setHeader("Nome").setWidth("20%");
        grid.addColumn(Estudante::getMatricula).setHeader("Matrícula").setWidth("40%");
        grid.addColumn(Estudante::getEmail).setHeader("Email").setWidth("20%");
        grid.addColumn(Estudante::getTelefone).setHeader("Telefone").setWidth("20%");
        grid.addColumn(Estudante::getNascimento).setHeader("Nascimento").setWidth("20%");

        grid.addComponentColumn(item -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> {
                new EquipamentosFormDialog(item, service, c -> {
                    gridListDataView.addItem(c);
                    gridListDataView.refreshAll();
                });
            });
            return editButton;
        }).setHeader("Editar").setWidth("85px").setResizable(true);

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, comentario) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        service.delete(comentario.getId());
                        UI.getCurrent().getPage().reload();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setWidth("45px").setResizable(true);

        Button btnAdicionar = new Button("Adicionar");
        btnAdicionar.addClickListener(event -> {
            // Cria uma instância do formulário
            new EquipamentosFormDialog(new Estudante(), service, c -> {
                gridListDataView.addItem(c);
                gridListDataView.refreshAll();
            });
        });
        add(btnAdicionar, grid);
    }

    static class EquipamentosFormDialog extends Dialog {
        @Serial
        private static final long serialVersionUID = 6055099001923416653L;

        public EquipamentosFormDialog(final Estudante estudante, final EstudantesService estudanteService, final Consumer<Estudante> consumer) {
            FormLayout formLayout = new FormLayout();

            Binder<Estudante> binder = new Binder<>(Estudante.class);

            TextField txtNome = new TextField("Nome");

            TextField txtDescricao = new TextField("Descrição");

            final DatePicker datePickerNascimento = new DatePicker("Data de Nascimento");
            add(datePickerNascimento);
//
            binder.forField(datePickerNascimento).asRequired()
                    .bind(Estudante::getNascimento, Estudante::setNascimento);
            //binder.forField(cbAtivo)
            //        .bind(Estudante::getAtivo, Estudante::setAtivo);
//
            //binder.forField(txtNome).asRequired()
            //        .withValidator(new StringLengthValidator("O nome deve ter entre 3 e 250 caracteres", 3, 250))
            //        .bind(Estudante::getNome, Estudante::setNome);
//
            //binder.forField(txtDescricao).asRequired()
            //        .withValidator(new StringLengthValidator("A descrição deve ter entre 3 e 250 caracteres", 3, 250))
            //        .bind(Estudante::getDescricao, Estudante::setDescricao);

            binder.setBean(estudante);

            formLayout.add(txtNome, txtDescricao,cbAtivo);
            add(formLayout);

            Button btnSalvar = new Button("Salvar", evento -> {
                if (binder.writeBeanIfValid(estudante)) {
                    consumer.accept(estudante);
                    estudanteService.salvar(estudante);
                    close();
                } else {
                    Notification.show("Preencha todos os campos corretamente.");
                }
            });

            Button cancelButton = new Button("Cancelar", e -> close());

            getFooter().add(btnSalvar);
            getFooter().add(cancelButton);

            open();
        }
    }
}
