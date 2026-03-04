package org.example.inventario_panelero.Model;
public enum Categoria {

    AZUCAR("AZ"), //categoria azucar con prefijo
    PANELA_EN_POLVO("PV"),//categoria panela en polvo con prefijo
    PASTILLA("PP"),//categoria pastilla con prefijo
    PANELA_DE_SABORES("PS");//categoria panela de sabores con prefijo


    private final String prefijo; // Atributo constante que almacena el prefijo asociado a la categoría

    Categoria(String prefijo) { // Constructor que recibe el prefijo como parámetro
        this.prefijo = prefijo; // Asigna el valor recibido al atributo prefijo de la clase
    }

    public String getPrefijo() { // Método getter que permite obtener el valor del prefijo
        return prefijo; // Retorna el prefijo almacenado
    }
}