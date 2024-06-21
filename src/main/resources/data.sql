insert into sample_person(id,first_name,last_name,occupation,role) values (1,'Eula','Lane','Insurance Clerk','Worker')
insert into sample_person(id,first_name,last_name,occupation,role) values (2,'Barry','Rodriquez','Mortarman','Manager')
insert into sample_person(id,first_name,last_name,occupation,role) values (3,'Eugenia','Selvi','Beer Coil Cleaner','External')
insert into sample_person(id,first_name,last_name,occupation,role) values (4,'Alejandro','Miles','Scale Attendant','Worker')
insert into sample_person(id,first_name,last_name,occupation,role) values (5,'Cora','Tesi','Clinical Audiologist','Supervisor')

--Inserindo os estudantes
INSERT INTO estudante (id, nome, nascimento, email, telefone, matricula) VALUES (1,'João Vitor Dandolini','2006-06-15', 'joao_vd_carvalho@estudante.sesisenai.org.br', '48997642427', 20241023)
INSERT INTO estudante (id, nome, nascimento, email, telefone, matricula) VALUES (2,'Keidi Teixeira', '2006-08-11', 'keidi_barbieri@estudante.sesisenai.org.br', '48992942632', 20242036)

insert into application_user (id, username,name, hashed_password, estudante_id) values ('1','user','John Normal','$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe', 1)
insert into user_roles (user_id, roles) values ('1', 'ESTUDANTE')
insert into application_user (id, username, name, hashed_password, estudante_id) values ('2','admin','Emma Powerful','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.', 2)
insert into user_roles (user_id, roles) values ('2', 'ESTUDANTE')
insert into user_roles (user_id, roles) values ('2', 'BIBLIOTECARIA')

INSERT INTO livro (id, titulo, autor, editora, ano) VALUES (1,'Orgulho e Preconceito' ,'Jane Austen', 'T. Egerton, Whitehall', 1813)
INSERT INTO livro (id, titulo, autor, editora, ano) VALUES (2   ,'1984', 'George Orwell', 'Secker & Warburg', 1949)
INSERT INTO livro (id, titulo, autor, editora, ano) VALUES (3,'Cem Anos de Solidão','Gabriel García Márquez', 'Editorial Sudamericana', 1967)
INSERT INTO livro (id, titulo, autor, editora, ano) VALUES (4,'O Grande Gatsby', 'F. Scott Fitzgerald', 'Charles Scribner''s Sons', 1925)
INSERT INTO livro (id, titulo, autor, editora, ano) VALUES (5,'A Menina que Roubava Livros','Markus Zusak', 'Picador (Austrália), Knopf (EUA)', 2005)


-- Inserindo os emprestimos
INSERT INTO emprestimo (id, livro_id, estudante_id, devolucao,  data_entrega, data_emprestimo) VALUES (1, 1, 1, true, '2021-08-02',  '2021-08-22')
INSERT INTO emprestimo (id, livro_id, estudante_id, devolucao,  data_entrega, data_emprestimo) VALUES (2, 2, 2, false, '2023-07-03',  '2023-07-27')
INSERT INTO emprestimo (id, livro_id, estudante_id, devolucao,  data_entrega, data_emprestimo) VALUES (3, 3, 1, true, '2022-09-05',  '2022-10-01')
INSERT INTO emprestimo (id, livro_id, estudante_id, devolucao,  data_entrega, data_emprestimo) VALUES (4, 4, 2, false, '2024-02-04',  '2024-02-29')
INSERT INTO emprestimo (id, livro_id, estudante_id, devolucao,  data_entrega, data_emprestimo) VALUES (5, 5, 1, true, '2024-06-15',  '2024-07-01')


