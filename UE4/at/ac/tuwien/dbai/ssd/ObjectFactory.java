
package at.ac.tuwien.dbai.ssd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.ac.tuwien.dbai.ssd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DescTypeB_QNAME = new QName("", "b");
    private final static QName _DescTypeI_QNAME = new QName("", "i");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.ac.tuwien.dbai.ssd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MovesType }
     * 
     */
    public MovesType createMovesType() {
        return new MovesType();
    }

    /**
     * Create an instance of {@link InfoType }
     * 
     */
    public InfoType createInfoType() {
        return new InfoType();
    }

    /**
     * Create an instance of {@link InfoType.Players }
     * 
     */
    public InfoType.Players createInfoTypePlayers() {
        return new InfoType.Players();
    }

    /**
     * Create an instance of {@link Game }
     * 
     */
    public Game createGame() {
        return new Game();
    }

    /**
     * Create an instance of {@link DescType }
     * 
     */
    public DescType createDescType() {
        return new DescType();
    }

    /**
     * Create an instance of {@link MovesType.Roll }
     * 
     */
    public MovesType.Roll createMovesTypeRoll() {
        return new MovesType.Roll();
    }

    /**
     * Create an instance of {@link MovesType.Piece }
     * 
     */
    public MovesType.Piece createMovesTypePiece() {
        return new MovesType.Piece();
    }

    /**
     * Create an instance of {@link InfoType.Winner }
     * 
     */
    public InfoType.Winner createInfoTypeWinner() {
        return new InfoType.Winner();
    }

    /**
     * Create an instance of {@link InfoType.Players.Screenname }
     * 
     */
    public InfoType.Players.Screenname createInfoTypePlayersScreenname() {
        return new InfoType.Players.Screenname();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DescType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "b", scope = DescType.class)
    public JAXBElement<DescType> createDescTypeB(DescType value) {
        return new JAXBElement<DescType>(_DescTypeB_QNAME, DescType.class, DescType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DescType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "i", scope = DescType.class)
    public JAXBElement<DescType> createDescTypeI(DescType value) {
        return new JAXBElement<DescType>(_DescTypeI_QNAME, DescType.class, DescType.class, value);
    }

}
