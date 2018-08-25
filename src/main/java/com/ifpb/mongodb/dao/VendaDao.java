package com.ifpb.mongodb.dao;

import com.ifpb.mongodb.connection.MongoConnectionPOJO;
import com.ifpb.mongodb.models.ItemVenda;
import com.ifpb.mongodb.models.Venda;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class VendaDao {

    private MongoCollection collection;

    public VendaDao() {
        collection = new MongoConnectionPOJO().getCollection("Venda", Venda.class);
    }

    public void salvar(Venda venda) {
        collection.insertOne(venda);
    }

    public List<Venda> listar() {
        MongoCursor cursor = collection.find().iterator();

        List<Venda> vendas = new ArrayList<>();

        while (cursor.hasNext()) {
            vendas.add((Venda) cursor.next());
        }

        return vendas;
    }

    public Venda busca(int cod) {
        MongoCursor cursor = collection.find(eq("codigo", cod)).iterator();
        return (Venda) cursor.next();
    }

    public boolean deletar(int cod) {
        DeleteResult result = collection.deleteOne(eq("codigo", cod));

        return result.getDeletedCount() > 0;
    }

    public boolean insereItem(int cod, ItemVenda item) {
        if (busca(cod) != null) {
            UpdateResult result = collection.updateOne(eq("codigo", cod), push("itens", new Document("produto", item.getProduto())
                    .append("quantidade", item.getQuantidade())));
            return result.getModifiedCount() > 0;
        }
        return false;
    }
}
