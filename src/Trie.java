import java.util.HashMap;

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
     * Verifica si el Trie contiene la palabra a buscar
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
     * @param word palabra a buscar
     * @return nodo de la ultima letra de la palabra buscada, o null si la palabra no se encuentra en el Trie
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
     * Borra
     * @param word
     * @return
     */
    public boolean deleteKey(String word){
        //Con searchNode buscamos la ultima letra de word
        TrieNode actualNode=searchNode(word);
        //Si no encuentra la palabra no se puede borrar, por lo tanto se retorna un falso
        if(actualNode==null){
            return false;
        }
        //al borrarse word dejara de ser considerado como palabra
        actualNode.setWord(false);
        //recorremos desde la ultima letra de word hasta el inicio
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
     *
     * @param word
     * @return
     */
    public boolean insertKey(String word){
        TrieNode t=root;
        HashMap<Character, TrieNode> children = root.getChildren();
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            //Si la letra existe se asigna como nuevo actual
            if(children.containsKey(c)){
                t = children.get(c);
            }
            //sino se crea y pone
            else{
                t = new TrieNode(t);
                children.put(c, t);
            }
            children = t.getChildren();
            //si la letra es la ultima letra de la palabra
            if(i==word.length()-1)
                t.setWord(true);
        }
        return false;
    }
}
