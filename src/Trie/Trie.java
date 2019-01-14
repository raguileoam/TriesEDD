package Trie;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trie {
    public TrieNode root;

    /**
     * Constructor donde se instancia el nodo inicial (root). Este nodo obviamente no tiene un nodo padre
     * por lo tanto este parametro(nodo padre) es nulo.
     */
    public Trie() {
        this.root = new TrieNode(null);
    }

    /**
     * Verifica si el Trie.Trie contiene la palabra a buscar
     * @param word palabra a buscar
     * @return booleano que determina si la palabra existe o no
     */
    public boolean contains(String word){
        TrieNode node = searchNode(word);
        if(node != null && node.isWord())
            return true;
        else
        return false;
    }

    /**
     * Encuentra trieNode de la ultima letra de la palabra a buscar
     * @param word Palabra a buscar
     * @return Nodo de la ultima letra de la palabra buscada, o null si la palabra no se encuentra en el Trie.Trie
     */
    public TrieNode searchNode(String word){
        HashMap<Character, TrieNode> actualChildren = root.getChildren();
        TrieNode actualNode = null;
        //Se recorrera cada letra de la palabra str y se buscara dentro de la estructura.
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(actualChildren.containsKey(c)){
                actualNode = actualChildren.get(c);
                actualChildren = actualNode.getChildren();
            }else{
                return null;
            }
        }

        return actualNode;
    }

    /**
     * En primera instancia buscaremos si la palabra a borrar realmente existe. Si es así tomamos la último nodo y empezamos a borrar cada letra hasta la primera letra de la palabra bajo ciertas condiciones:
     * Si cada nodo vinculado con la palabra no tiene ramificaciones relacionadas con otras palabras, es decir el árbol es vertical sin ramificaciones. Se borran todos los nodos vinculados a la palabra.
     * Si algún nodo tiene ramificaciones no relacionadas con la palabra a borrar. Se borra hasta ese nodo, para que no borremos algún nodo vinculado a otra a palabra.
     * Si algún nodo es final de alguna palabra que no sea la que se está borrando. Se borra hasta ese nodo.
     * Si la letra final de la palabra es parte de otra palabra. No se borra nada pero se deja de
     * considerar la palabra como ‘palabra’ (booleano de final de palabra se vuelve falso).
     * @param word palabra a borrar
     * @return True si se cumplio algunas de las condiciones.
     */
    public boolean deleteKey(String word){
        //Con searchNode buscamos la ultima letra de word
        TrieNode actualNode=searchNode(word);
        //Si no encuentra la palabra no se puede borrar, por lo tanto se retorna un falso
        if(actualNode==null){
            return false;
        }
        //Al borrarse word dejara de ser considerado como palabra
        actualNode.setWord(false);
        //Recorremos desde la ultima letra de word hasta el inicio
        for(int i=word.length()-1;i>=0;i--){
            char letra=word.charAt(i);
            //Si en la posicion en donde estamos ya hay una palabra existente se detiene el borrado
            //Si existen mas childrens aparte de la letra que se intenta borrar se detiene el borrado
            if(actualNode.isWord() || !actualNode.getChildren().isEmpty()){
                break;
            }
            //Si no es así dirigimos el puntero a el padre de la letra actual y le borramos la letra actual como children
            actualNode=actualNode.getParent();
            actualNode.getChildren().remove(letra);
        }

        return true;
    }

    /**
     *Para agregar una palabra al Trie.Trie cada letra debe ser insertada como un nodo individual.
     * En primera instancia se busca si el nodo root contiene en sus nodos hijos a la primera letra.
     * Si es así se toma ese nodo y se busca si ese nodo contiene en sus nodos hijos a la segunda letra. Y así sucesivamente.
     * Si la letra no existe como nodo hijo se agrega y se toma el nodo agregado para buscar la siguiente letra.
     * Cuando se llega a la última letra y por ende nodo final, marcamos este último nodo como fin de la palabra.
     * @param word palabra
     * @return True si la palabra se agrego correctamente
     */
    public boolean insertKey(String word){
        TrieNode node=root;
        HashMap<Character, TrieNode> children = root.getChildren();
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            //Si la letra existe se asigna como nuevo actual
            if(children.containsKey(c)){
                node = children.get(c);
            }
            //sino se crea y pone
            else{
                node = new TrieNode(node);
                children.put(c, node);
            }
            children = node.getChildren();
            //si la letra es la ultima letra de la palabra
            if(i==word.length()-1)
                node.setWord(true);
        }
        return false;
    }

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
}
