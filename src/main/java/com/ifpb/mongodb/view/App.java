package com.ifpb.mongodb.view;

import com.ifpb.mongodb.dao.VendaDao;
import com.ifpb.mongodb.models.ItemVenda;
import com.ifpb.mongodb.models.Produto;
import com.ifpb.mongodb.models.Venda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        VendaDao daoVenda = new VendaDao();
      
        Produto produto = new Produto(1, "neeeew", 1);
        Produto produto2 = new Produto(8, "Produto 2 do segundo teste", 10);
        
        ItemVenda item = new ItemVenda(produto, 3);
        ItemVenda item2 = new ItemVenda(produto2, 6);
        
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(item);
        itens.add(item2);
                
        Venda venda = new Venda(2, itens, LocalDateTime.now());
        
        daoVenda.insereItem(2, item);

    }
}
