package Trie;

public class Main {
    public static void main(String[] args) {

        String texto="unos nodos unidos por una union";
        System.out.println("Agregando texto \""+texto+"\"");
        //Obtener tokens separa las palabras y usa Trie.Trie.insertKey para agregarlas al Trie.Trie
        Trie trie=Trie.obtenerTokens(texto);
        System.out.println("El trie contiene la palabra unidos: "+trie.contains("unidos"));
        System.out.println("El trie contiene la palabra unidad: "+trie.contains("unidad"));
        System.out.println("Borrando palabra \"unidos\"");
        trie.deleteKey("unidos");
        System.out.println("El trie contiene la palabra unidos: "+trie.contains("unidos"));


    }
}

