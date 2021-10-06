package br.lucas.appbuscacep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private static List<Endereco> ed;

    public static void montar(JSONArray jarray)
    {
        ed = new ArrayList<>();
        JSONObject obj;
        for (int i = 0; i < jarray.length() ; i++) {
            try {
                obj = jarray.getJSONObject(i);
                ed.add(new Endereco(obj.getString("cep"),
                                    obj.getString("logradouro"),
                                    obj.getString("complemento"),
                                    obj.getString("bairro"),
                                    obj.getString("localidade"),
                                    obj.getString("uf"),
                                    obj.getString("ibge"),
                                    obj.getString("gia"),
                                    obj.getString("ddd"),
                                    obj.getString("siafi")));
                System.out.println(ed.get(i).getLogradouro());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public  List<Endereco> getEnds()
    {
        return ed;
    }
}
