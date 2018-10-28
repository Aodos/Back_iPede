SELECT * FROM ipededata.tb_item;

insert into ipededata.tb_item (nme_nome_item, dsc_descricao_item, vlr_valor_item, flg_disponibilidade_item, url_foto_item, fk_idt_id_restaurante)
value ('B.M.T.®','O sanduíche que vai acabar com toda a sua fome. Servido em pão fresquinho, com fatias de salame, peperoni, presunto, vegetais e condimentos à sua escolha.',7.99,1,null,4);

select * from ipededata.tb_item where idt_id_item = 2;

update ipededata.tb_item set nme_nome_item = ?, dsc_descricao_item = ?, vlr_valor_item = ?, flg_disponibilidade_item = ?, url_foto_item = ?, fk_idt_id_restaurante = ?
where idt_id_item = ?;

DELETE FROM ipededata.tb_item
WHERE idt_id_item = 1;