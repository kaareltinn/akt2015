public class FiniteAutomaton {
    /**
     * Selle meetodiga annab automaadi koostaja teada, millised olekud automaadis
     * esinevad. isAcceptingState ütleb, kas tegemist on lõppolekuga.
     */
    public void addState(Integer stateId, boolean isAcceptingState) {

    }

    /**
     * Selle meetodiga määratakse algolek. Võib eeldada, et eelnevalt on see olek
     * automaati lisatud.
     */
    public void setStartState(Integer id) {

    }

    /**
     * Selle meetodiga lisatakse uus üleminek. Epsilon-ülemineku korral on label==null.
     * Võib eeldada, et olekud fromState ja toState on juba eelnevalt lisatud.
     */
    public void addTransition(Integer fromState, Character label, Integer toState) {

    }

    /**
     * See meetod peab ütlema, kas automaat tunneb ära näidatud sisendi.
     */
    public boolean accepts(String input) {
        return false;
    }

    /**
     * Seda meetodit ei hinnata ja seda ei pea muutma, aga läbikukkunud testide korral
     * antakse sulle automaadi kirjelduseks just selle meetodi tagastusväärtus.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}