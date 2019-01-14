import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author raguileoam
 */
public class WordFind {
    /**
     * Devuelve los tokens que coinciden con el patrón de expresión regular
     * de la cadena de texto del documento.
     * @param texto
     * @return Una lista de tokens del texto del documento que coinciden
     * con el patrón de expresiones regulares
     */

    public static Trie obtenerTokens(String texto){
        String patron="[!?.]+|[a-zA-Z]+";
        Trie tokens=new Trie();
        Pattern tokDivisor = Pattern.compile(patron);
        Matcher match = tokDivisor.matcher(texto);
        while (match.find()) {
            tokens.insertKey(match.group());
        }
        return tokens;
    }

    public static void main(String[] args) {
        Trie trie=obtenerTokens("unos nodos unidos por una union");
        System.out.println(trie.searchNode("u"));


    }
}

