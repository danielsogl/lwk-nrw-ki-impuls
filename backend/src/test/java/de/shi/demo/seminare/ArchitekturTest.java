package de.shi.demo.seminare;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

/**
 * Regeln aus AGENTS.md als Test. Läuft wie Lint bei jedem Build, für Menschen und Agenten.
 */
@AnalyzeClasses(packages = "de.shi.demo.seminare", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitekturTest {

    // Neue Bibliotheken nur nach Rückfrage: Wer eine einführt, erweitert diese Liste im Review.
    @ArchTest
    static final ArchRule nurFreigegebeneBibliotheken = classes()
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage("java..", "jakarta..", "org.springframework..", "de.shi.demo.seminare..")
            .because("neue Abhängigkeiten nur nach Rückfrage (AGENTS.md)");

    @ArchTest
    static final ArchRule keineFeldinjektion = NO_CLASSES_SHOULD_USE_FIELD_INJECTION
            .because("Konstruktor-Injection (AGENTS.md)");
}
