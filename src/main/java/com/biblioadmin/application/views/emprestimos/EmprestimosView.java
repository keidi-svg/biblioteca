package com.biblioadmin.application.views.emprestimos;

import com.biblioadmin.application.components.appnav.UploadField;
import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.service.EstudantesService;
import com.biblioadmin.application.views.MainLayout;
import com.biblioadmin.application.views.estudantes.EstudanteView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import org.springframework.util.unit.DataSize;

import javax.annotation.security.PermitAll;
import java.io.*;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@PageTitle("Equipamentos")
@Route(value = "equipamentos", layout = MainLayout.class)
@RouteAlias(value = "none", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class EmprestimosView extends VerticalLayout {
    public EmprestimosView(EstudantesService service) {
        Grid<Estudante> grid = new Grid<>();

        final GridListDataView<Estudante> gridListDataView = grid.setItems(service.listarTodos());
        grid.addColumn(Estudante::getId).setHeader("ID").setWidth("60px");
        grid.addColumn(Estudante::getNome).setHeader("Nome").setWidth("20%");
       //grid.addColumn(Estudante::getDescricao).setHeader("Descrição").setWidth("40%");
       //grid.addColumn(Estudante::getData).setHeader("Data").setWidth("20%");
       // grid.addColumn(equipamentos -> equipamentos.getAtivo() ? "Ativo" : "Inativo").setHeader("Ativo").setComparator(Estudante::getAtivo).setWidth("10%");

        grid.addComponentColumn(item -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> {
                //new EstudanteView.EquipamentosFormDialog(item, service, c -> {
                //    gridListDataView.addItem(c);
                //    gridListDataView.refreshAll();
                //});
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
            //new EstudanteView.EquipamentosFormDialog(new Estudante(), service, c -> {
            //    gridListDataView.addItem(c);
            //    gridListDataView.refreshAll();
            //});
        });
        add(btnAdicionar, grid);
    }

    static class EquipamentosFormDialog extends Dialog {
        @Serial
        private static final long serialVersionUID = 6055099001923416653L;

        public EquipamentosFormDialog(final Estudante equipamentos, final EstudantesService estudantesService, final Consumer<Estudante> consumer) {
            FormLayout formLayout = new FormLayout();

            Binder<Estudante> binder = new Binder<>(Estudante.class);

            TextField txtNome = new TextField("Nome");

            TextField txtDescricao = new TextField("Descrição");

            var cbAtivo = new Checkbox("Ativo");

            final UploadField upload = new UploadField().withMaxFiles(1).withMaxFileSize((int) DataSize.ofMegabytes(25).toBytes());
            if (equipamentos.getId() == null) {
                formLayout.add(upload, 5);
            }

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

            binder.setBean(equipamentos);

            formLayout.add(txtNome, txtDescricao,cbAtivo);
            add(formLayout);

            Button btnSave = new Button("Salvar", event -> {
                if (binder.writeBeanIfValid(equipamentos)) {
                    if (equipamentos.getId() == null) {
                       // equipamentos.setData(LocalDateTime.now());
                    }
                    try (InputStream is = upload.getInputStream()) {
                     //   consumer.accept(estudantesService.salvar(equipamentos, upload.getFileName(), is.readAllBytes()));
                        this.close();
                    } catch (IllegalArgumentException | IllegalStateException error) {
                        Notification.show(error.getMessage());
                    } catch (Exception ex) {
                        Notification.show("Erro desconhecido " + ex);
                    }
                }
            });

            Button cancelButton = new Button("Cancelar", e -> close());

            getFooter().add(btnSave);
            getFooter().add(cancelButton);

            open();
        }
    }
}