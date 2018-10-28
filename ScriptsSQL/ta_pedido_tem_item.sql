SELECT * FROM ipededata.ta_pedido_tem_item;

update ipededata.ta_pedido_tem_item set qtd_item = 2
where fk_idt_id_item = 3;

insert into ipededata.ta_pedido_tem_item (fk_idt_id_pedido, fk_idt_id_item, qtd_item)
values(7,3,2);

DELETE FROM ipededata.ta_pedido_tem_item
WHERE fk_idt_id_pedido = 4 and fk_idt_id_item = 3;

#		this.idItem = idItem;
#		this.idPedido = idPedido;
#		this.nomeItem = nomeItem;
#		this.qntItem = qntItem;
#		this.valorItem = valorItem;

select ipededata.tb_item.idt_id_item, ipededata.tb_pedido.idt_id_pedido, ipededata.tb_item.nme_nome_item,
ipededata.ta_pedido_tem_item.qtd_item, ipededata.tb_item.vlr_valor_item from ipededata.ta_pedido_tem_item
INNER JOIN ipededata.tb_item on ipededata.ta_pedido_tem_item.fk_idt_id_item = ipededata.tb_item.idt_id_item
INNER JOIN ipededata.tb_pedido on ipededata.ta_pedido_tem_item.fk_idt_id_pedido = ipededata.tb_pedido.idt_id_pedido
where ipededata.ta_pedido_tem_item.fk_idt_id_pedido = 7;