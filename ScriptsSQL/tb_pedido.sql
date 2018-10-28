SELECT * FROM ipededata.tb_pedido;

insert into ipededata.tb_pedido (dta_data_pedido, sts_situacao_pagamento, fk_idt_id_cliente, fk_idt_id_restaurante)
values (now(), 'Pendente', 5, 4);

select * from ipededata.tb_pedido where sts_situacao_pagamento = 'Pendente' and fk_idt_id_cliente = 6;

#Select para pegar valor total do pedido
select sum(pti.qtd_item * i.vlr_valor_item) from ipededata.ta_pedido_tem_item pti
INNER JOIN ipededata.tb_item i ON i.idt_id_item = pti.fk_idt_id_item
where fk_idt_id_pedido = 7;

update ipededata.tb_pedido p set vlr_valor_total = ? where idt_id_pedido = 7;

DELETE FROM ipededata.tb_pedido
WHERE fk_idt_id_cliente = 4;

#		this.idPedido = idPedido;
#		this.idCliente = idCliente;
#		this.idRestaurante = idRestaurante;
#		this.nomeCliente = nomeCliente;
#		this.nomeRestaurante = nomeRestaurante;
#		this.valorTotal = valorTotal;
#		this.dataPedido = dataPedido;
#		this.itensPedido = itensPedido;

select ipededata.tb_pedido.idt_id_pedido, ipededata.tb_cliente.idt_id_cliente, ipededata.tb_restaurante.idt_id_restaurante,
ipededata.tb_cliente.nme_primeiro_nome, ipededata.tb_restaurante.nme_nome_restaurante, ipededata.tb_pedido.vlr_valor_total, ipededata.tb_pedido.dta_data_pedido
from ipededata.tb_pedido
INNER JOIN ipededata.tb_cliente on ipededata.tb_pedido.fk_idt_id_cliente = ipededata.tb_cliente.idt_id_cliente
INNER JOIN ipededata.tb_restaurante on ipededata.tb_pedido.fk_idt_id_restaurante = ipededata.tb_restaurante.idt_id_restaurante
where ipededata.tb_pedido.idt_id_pedido = 4;


