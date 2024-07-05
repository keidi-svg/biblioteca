package com.biblioadmin.application.views.emprestimos;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.views.MainLayout;
import com.biblioadmin.application.views.estudantes.EstudanteService;
import com.biblioadmin.application.views.livros.LivroService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.io.*;
import java.sql.SQLException;
import java.util.function.Consumer;

@PageTitle("Emprestimos")
@Route(value = "emprestimos", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class EmprestimosView extends VerticalLayout {

    public EmprestimosView(EmprestimoService service, EstudanteService estudantesService, LivroService livrosService) throws SQLException {
        Grid<Emprestimo> grid = new Grid<>();

        final GridListDataView<Emprestimo> gridListDataView = grid.setItems(service.listarTodos());
        grid.addColumn(Emprestimo::getId).setHeader("ID").setWidth("60px");
        grid.addColumn(c -> c.getEstudante().getNome()).setHeader("Estudante").setWidth("20%");
        grid.addColumn(c -> c.getLivro().getTitulo()).setHeader("Livro").setWidth("20%");
        grid.addColumn(Emprestimo::getDataEmprestimo).setHeader("Data Empréstimo").setWidth("20%");
        grid.addColumn(Emprestimo::getDataEntrega).setHeader("Data Entrega").setWidth("20%");

        grid.addColumn(
                new ComponentRenderer<>(ComboBox::new, (comboBox, emprestimo) -> {
                    comboBox.setItems("Sim", "Não");
                    comboBox.setValue(emprestimo.getDevolucao() ? "Sim" : "Não");
                    comboBox.addValueChangeListener(event -> {
                        boolean devolvido = "Sim".equals(event.getValue());
                        emprestimo.setDevolucao(devolvido); // Atualizar o objeto Emprestimo
                        service.updateDevolucao(emprestimo.getId(), devolvido); // Chamar o método salvar
                        Notification.show("Status de devolução atualizado");
                        gridListDataView.refreshItem(emprestimo);
                    });
                })).setHeader("Recebido").setWidth("10%");

        grid.addComponentColumn(item -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> {
                try {
                    new EmprestimosFormDialog(item, service, livrosService, estudantesService, c -> {
                        gridListDataView.addItem(c);
                        gridListDataView.refreshAll();
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return editButton;
        }).setHeader("Editar").setWidth("85px").setResizable(true);

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, emprestimo) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        service.delete(emprestimo.getId());
                        UI.getCurrent().getPage().reload();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setWidth("45px").setResizable(true);

        Button btnAdicionar = new Button("Adicionar");
        btnAdicionar.addClickListener(event -> {
            try {
                new EmprestimosFormDialog(new Emprestimo(), service, livrosService, estudantesService, c -> {
                    gridListDataView.addItem(c);
                    gridListDataView.refreshAll();
                });
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        add(btnAdicionar, grid);
    }

    static class EmprestimosFormDialog extends Dialog {
        @Serial
        private static final long serialVersionUID = 6055099001923416653L;

        public EmprestimosFormDialog(final Emprestimo emprestimo, final EmprestimoService emprestimosService, final LivroService livrosService, final EstudanteService estudantesService, final Consumer<Emprestimo> consumer) throws SQLException {
            FormLayout formLayout = new FormLayout();

            Binder<Emprestimo> binder = new Binder<>(Emprestimo.class);

            final ComboBox<Estudante> cbEstudante = new ComboBox<>("Estudante");
            cbEstudante.setItems(estudantesService.listarTodos());
            cbEstudante.setItemLabelGenerator(Estudante::getNome);

            binder.forField(cbEstudante).asRequired()
                    .bind(Emprestimo::getEstudante, Emprestimo::setEstudante);

            final ComboBox<Livro> cbLivro = new ComboBox<>("Livro");
            cbLivro.setItems(livrosService.listarTodos());
            cbLivro.setItemLabelGenerator(Livro::getTitulo);

            binder.forField(cbLivro).asRequired()
                    .bind(Emprestimo::getLivro, Emprestimo::setLivro);

            DatePicker dataEmprestimo = new DatePicker("Data Emprestimo");
            add(dataEmprestimo);
            DatePicker dataEntrega = new DatePicker("Data Entrega");
            add(dataEntrega);
            dataEmprestimo
                    .addValueChangeListener(e -> dataEntrega.setMin(e.getValue()));
            dataEntrega.addValueChangeListener(
                    e -> dataEmprestimo.setMax(e.getValue()));

            binder.forField(dataEmprestimo).asRequired()
                    .bind(Emprestimo::getDataEmprestimo, Emprestimo::setDataEmprestimo);

            binder.forField(dataEntrega).asRequired()
                    .bind(Emprestimo::getDataEntrega, Emprestimo::setDataEntrega);

            binder.setBean(emprestimo);

            formLayout.add(cbEstudante, cbLivro, dataEmprestimo, dataEntrega);
            add(formLayout);

            Button btnSave = new Button("Salvar", event -> {
                if (binder.writeBeanIfValid(emprestimo)) {
                    consumer.accept(emprestimo);
                    if(emprestimo.getId() == null || emprestimo.getId() == 1){
                        emprestimo.setDevolucao(false);
                    }
                    emprestimosService.salvar(emprestimo);
                    close();
                } else {
                    Notification.show("Preencha todos os campos corretamente.");
                }
            });

            Button cancelButton = new Button("Cancelar", e -> close());

            getFooter().add(btnSave);
            getFooter().add(cancelButton);

            open();
        }
    }
}