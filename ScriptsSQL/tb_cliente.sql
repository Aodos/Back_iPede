SELECT * FROM ipededata.tb_cliente;

insert into ipededata.tb_cliente (nme_primeiro_nome, nme_ultimo_nome, cpf_cliente, eml_email, cel_celular, pwd_senha, ddd_ddd)
values ('Thiago', 'Faria', '11111111111', 'thiago@email.com', '988888888', 'SenhaThiago', 61);

select * from ipededata.tb_cliente where idt_id_cliente = 1;

update ipededata.tb_cliente set nme_primeiro_nome = ?, nme_ultimo_nome = ?, cpf_cliente = ?, eml_email = ?,
cel_celular = ?, pwd_senha = ?, ddd_ddd = ?, url_foto_cliente = ? where idt_id_restaurante = ?;

DELETE FROM ipededata.tb_cliente WHERE idt_id_cliente = ?;