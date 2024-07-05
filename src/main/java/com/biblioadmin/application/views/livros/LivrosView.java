package com.biblioadmin.application.views.livros;

import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;
import java.io.Serial;
import java.sql.SQLException;
import java.util.function.Consumer;

@PageTitle("Livros")
@Route(value = "livros", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class LivrosView extends VerticalLayout {
        public LivrosView(LivroService service) throws SQLException {

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
                    new LivrosView.LivrosFormDialog(item, service, c -> {
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
                new LivrosView.LivrosFormDialog(new Livro(), service, c -> {
                    gridListDataView.addItem(c);
                    gridListDataView.refreshAll();
                });
            });
            add(btnAdicionar, grid);
        }

        static class LivrosFormDialog extends Dialog {
            @Serial
            private static final long serialVersionUID = 6055099001923416653L;

            public LivrosFormDialog(final Livro livro, final LivroService livrosService, final Consumer<Livro> consumer) {
                FormLayout formLayout = new FormLayout();

                Binder<Livro> binder = new Binder<>(Livro.class);
                // Cria os campos de texto do formulário
                TextField txtTitulo = new TextField("Titulo");

                binder.forField(txtTitulo).asRequired()
                        .withValidator(new StringLengthValidator("O título do livro deve estar entre 3 e 255 caracteres", 3, 255))
                        .bind(Livro::getTitulo, Livro::setTitulo);

                TextField txtAutor = new TextField("Autor");

                binder.forField(txtAutor).asRequired()
                        .withValidator(new StringLengthValidator("O título do livro deve estar entre 3 e 255 caracteres", 3, 255))
                        .bind(Livro::getAutor, Livro::setAutor);

                TextField txtEditora = new TextField("Editora");

                binder.forField(txtEditora).asRequired()
                        .withValidator(new StringLengthValidator("O título do livro deve estar entre 3 e 255 caracteres", 3, 255))
                        .bind(Livro::getEditora, Livro::setEditora);

                TextField txtAno = new TextField("Ano");

                binder.forField(txtAno).asRequired()
                        .withValidator(new StringLengthValidator("O ano deve ter 4 digitos", 4, 4))
                        .withConverter(new StringToIntegerConverter("Escreva um ano"))
                        .bind(Livro::getAno, Livro::setAno);


                binder.setBean(livro);

                formLayout.add(txtTitulo, txtAutor, txtEditora, txtAno);
                add(formLayout);

                Button btnSalvar = new Button("Salvar", evento -> {
                    if (binder.writeBeanIfValid(livro)) {
                        consumer.accept(livro);
                        livrosService.salvar(livro);
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
