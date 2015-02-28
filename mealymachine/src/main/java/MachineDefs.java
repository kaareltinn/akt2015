import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Siin on vaja defineerida järgmised masinad. Kõige paremini saab nendest aru, kui hoolikalt
 * teste läbi vaadata. Seekord on kõik testid avalikud. Me võime aga kosmeetilisi muudatusi nendele teha,
 * seega ära päris ainult nende pealt progremmeeri.
 */
public class MachineDefs {


    /**
     * Tulemusena tahaks saada masin, mis realiseerib meie HtmlStrip ülesanne.
     * Siin on alustatud, aga väike viga on sees... Lisaks võiks masina täiendada, et
     * mõlemat tüüpi jutumärkidega arvestaks.
     */
    public static MealyMachine getHtmlMachine() {
        List<TableEntry> table = Arrays.asList(
                // TODO: Viga tuleks ära parandada:
                new TableEntry(0, '<', 1, "x"),
                new TableEntry(0, '_', 0, "_"),

                new TableEntry(1, '>', 0, ""),
                new TableEntry(1, '\'', 2, ""),
                new TableEntry(1, '_', 1, ""),

                new TableEntry(2, '\'', 1, ""),
                new TableEntry(2, '_', 2, "")

                // TODO: Lisada ka üleminekud tavaliste jutumärkide jaoks.
                // NB! Üleval on üleminek (1, '_', 1, ""), mis sobitub kõikide tähtedega.

        );
        return new MealyMachine(table);
    }


    /**
     * Masin, mis asendab iga teine 'x' tähega 'o', näiteks 'xxxx' → 'xoxo'.
     */
    public static MealyMachine getXoxoMachine() {
        // TODO: Ei tohiks olla liiga keeruline masin...
        return null;
    }

    /**
     * Siin on vaja plus-avaldist juppideks jagada, toppides etteantud eraldaja juppide vahele.
     * Kui eraldaja on näiteks '|', siis sisendi "10+kala++5" korral tahaks saada "10|+|kala|+|+|5".
     * Ma ei taha saada "10|+|kala|+||+|5"
     */
    public static MealyMachine getTokenizer(char delim) {
        // TODO: Samuti kahe seisundiga masin.
        // Masin on lehel: https://courses.cs.ut.ee/2015/AKT/spring/Main/PlusMiinus
        // Sealset viimast seisundit ei ole vaja ja väljundid on siin muidugi lihtsamad.
        return null;
    }

    /**
     * Sisendiks on sulgavaldis, kus on peidus üks dollarmärk. Tuleb tagastada dollarmärgi sügavus ehk
     * kui palju rohkem on dollarmärgini jõudes sulge avatud, kui seda on kinni pandud. Kuna lõpliku arv
     * seisunditega on seda võimatu lahendada, võib eeldada, et maksimaalne sügavus on 10.
     */
    public static MealyMachine getDepthMachine() {
        List<TableEntry> table = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // TODO: Lisada iga seisundi jaoks üleminekud siia.
        }
        return new MealyMachine(table);
    }

    /**
     * See on see kodutöö ülesanne, mis oli liiga lihtne. Nüüd on ta natuke raskem...  :)
     * See ei ole liiga tüütu, kui kasutada kirjavahemärkide lisamiseks abifunktsioon,
     * eeldades muidugi, et automaat on kõigepealt optimeeritud.
     */
    public static MealyMachine getFormatter() {
        // TODO: Lõpuks saab kodutöö korralikult lahendatud.
        return null;
    }


    /**
     * Aitab naljast, nüüd lõpuks saab natuke mõelda... Sisenditeks on binaararvud, mis lõppevad dollariga.
     * Tuleks öelda, mis on antud binaararvu väärtus kümnendsüsteemis, aga kuna meie automaadid on lõplikud,
     * siis saame ainult loendada teatud arvuni. Kui piir on ületatud, siis tuleb uuesti nullist lugema hakkata.
     * Teiste sõnadaga, me fikseerime arvu n ja masin arvutab, mis on binaararvu jääk jagamisel n-iga.
     */
    public static MealyMachine getBinaryMachine(int n) {
        // TODO: See on tegelikult väga lihtne, kui Algebra loengutest on midagi meeles...
        return null;
    }
}
