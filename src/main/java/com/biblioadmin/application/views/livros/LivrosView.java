package com.biblioadmin.application.views.livros;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.data.entity.User;
import com.biblioadmin.application.data.service.EstudantesService;
import com.biblioadmin.application.data.service.LivrosService;
import com.biblioadmin.application.data.service.UserService;
import com.biblioadmin.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@PageTitle("Comentarios")
@Route(value = "comentarios", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class LivrosView extends VerticalLayout {
        public LivrosView(LivrosService service, EstudantesService estudantesService, UserService userService) {

            Grid<Livro> grid = new Grid<>();
            final GridListDataView<Livro> gridListDataView = grid.setItems(service.listarTodos());
            grid.addColumn(Livro::getId).setHeader("ID").setResizable(true).setWidth("40px");
            grid.addColumn(Livro::getTitulo).setHeader("Título").setResizable(true).setWidth("70px");
            grid.addColumn(Livro::getAutor).setHeader("Autor").setResizable(true).setWidth("70px");
            grid.addColumn(Livro::getEditora).setHeader("Editora").setResizable(true).setWidth("50px");
            grid.addColumn(Livro::getAno).setHeader("Ano").setResizable(true).setWidth("50px");

            grid.addComponentColumn(item -> {
                Button editButton = new Button("Editar");
                editButton.addClickListener(event -> {
                    new LivrosView.LivrosFormDialog(item, userService, estudantesService, service, c -> {
                        gridListDataView.addItem(c);
                        gridListDataView.refreshAll();
                    });
                });
                return editButton;
            }).setHeader("Editar").setWidth("85px");

            grid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, livro) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> {
                            service.delete(livro.getId());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.TRASH));
                    })).setClassNameGenerator(item -> "align-left");

            Button btnAdicionar = new Button("Adicionar");
            btnAdicionar.addClickListener(event -> {
                // Cria uma instância do formulário
                new LivrosView.LivrosFormDialog(new Livro(),userService , estudantesService, service, c -> {
                    gridListDataView.addItem(c);
                    gridListDataView.refreshAll();
                });
            });
            add(btnAdicionar, grid);
        }

        static class LivrosFormDialog extends Dialog {
            @Serial
            private static final long serialVersionUID = 6055099001923416653L;

            public LivrosFormDialog(final Livro livros, UserService userService, EstudantesService estudantesService, final LivrosService livrosService, final Consumer<Livro> consumer) {
                FormLayout formLayout = new FormLayout();

                Binder<Livro> binder = new Binder<>(Livro.class);
                // Cria os campos de texto do formulário
                TextField txtNome = new TextField("Livros");

                //final ComboBox<Estudante> cbEquipamentos = new ComboBox<>("Equipamentos");
                //cbEquipamentos.setItems(estudantesService.listarTodos());
                //cbEquipamentos.setItemLabelGenerator(Estudante::getNome);
//
                //binder.forField(cbEquipamentos).asRequired()
                //        .bind(Livro::getEquipamento, Livro::setEquipamento);

                //final ComboBox<User> cbUser = new ComboBox<>("Usuários");
                //cbUser.setItems(userService.listarTodos());
                //cbUser.setItemLabelGenerator(User::getNome);
//
                //binder.forField(cbUser).asRequired()
                //        .bind(Livro::getUsuario, Livro::setUsuario);
//
                //binder.forField(txtNome).asRequired()
                //        .withValidator(new StringLengthValidator("O comentário deve ter entre 3 e 250 caracteres", 3, 50))
                //        .bind(Livro::getComentario, Livro::setComentario);
//
                //binder.setBean(comentarios);
//
                //// Abre o diálogo de edição do equipamentos
                //formLayout.add(txtNome, cbEquipamentos, cbUser);
                //add(formLayout);

                // Configura o diálogo para salvar o objeto Cliente quando o botão 'Salvar' for clicado
                //Button btnSalvar = new Button("Salvar", evento -> {
                //    if (comentarios.getId() == null) {
                //        comentarios.setDataComentario(LocalDateTime.now());
                //    }
                //    if (binder.writeBeanIfValid(comentarios)) {
                //        consumer.accept(comentarios);
                //        comentariosService.salvar(comentarios);
                //        close();
                //    } else {
                //        Notification.show("Preencha todos os campos corretamente.");
                //    }
                //});

                Button cancelButton = new Button("Cancelar", e -> close());

                //getFooter().add(btnSalvar);
                getFooter().add(cancelButton);

                // Abre o diálogo
                open();
            }

        }

}
