## Devátá iterace
symbolfreq implements comparable


Cvičení zaměřené na práci se vstupem a výstupem.


Upravte třídu `LabeledPolygon` tak, aby implementovala rozhraní `PolygonIO`.

1.  Metoda `read(InputStream)` vezme otevřený vstup obsahující pojmenované vrcholy,
    vrcholy načte a přidá je k existujícím vrcholům polygonu.
    Při jakékoliv chybě vstupu/výstupu nebo chybě formátu vstupních dat musí metoda atomicky selhat
    a vyhodit `IOException`. (atomicky = načítám všechno nebo nic)
    Formát vstupních dat je následující:
    *   Vstup je textový.
    *   Co vrchol, to jeden řádek.
    *   Každý řádek je ve formátu _"x y nazev vrcholu"_, tj. nejprve souřadnice vrcholu oddělené mezerou
        a poté název vrcholu (název může obsahovat mezery).
        Viz např. soubor _polygon-ok.txt_.

2.  Metoda `write(OutputStream)` zapíše vrcholy do daného výstupního proudu.
    Výstupní formát je stejný jako pro předchozí metodu.

3.  Metody `write(File)` a `read(File)` budou fungovat stejně jako předchozí,
    budou ale pracovat se souborem namísto vstupně-výstupního proudu.
    Vyhněte se opakování kódu!

4.  Vytvořte metodu `binaryWrite(OutputStream os)`, která bude do výstupního proudu zapisovat přímo,
    bez obalování writerem a přesto bude výsledek textový. Nápověda:
    *   Všechny souřadnice a ostatní text, který chceme zapsat binárně, musíme převést na pole bajtů.
        Třída `String` na to má přímo metodu.
    *   Je třeba vložit univerzální oddělovač konců řádků, `System.lineSeparator()`.
        Také znaky oddělovače konců řádků je nutné převést na bajty.

5.  Spuštěním třídy `Draw` se načte _polygon-ok.txt_ a [vykreslí se na obrazovce
    ](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images#iteration-09).

> Testy vytvářejí soubory `polygon-out.txt` a `polygon.bin` (jehož obsah by měl být při správné implementaci čitelný).
