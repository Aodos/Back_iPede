SELECT * FROM ipededata.tb_restaurante;

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE table ipededata.tb_restaurante; 
SET FOREIGN_KEY_CHECKS = 1;

insert into ipededata.tb_restaurante (end_endereco, nme_nome_restaurante, end_cidade, cep_restaurante, pwd_senha_restaurante, cpj_cnpj_restaurante, lat_latitude, lgt_longitude)
values ('Qs 1, Rua 210, Lote 40', 'McDonalds', 'Taguatinga', '71950904', 'SenhaMcDonalds', '63.865.822/0001-55', -45.222, -50.777);

SELECT * FROM ipededata.tb_restaurante where idt_id_restaurante = 1;

SHOW STATUS WHERE `variable_name` = 'Threads_connected';

update ipededata.tb_restaurante set end_endereco = ?, nme_nome_restaurante = ?, end_cidade = ?, cep_restaurante = ?, pwd_senha_restaurante = ?, cpj_cnpj_restaurante = ?, lat_latitude = ?, lgt_longitude = ? where idt_id_restaurante = ?;

DELETE FROM ipededata.tb_restaurante
WHERE idt_id_restaurante = 2;
