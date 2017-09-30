module easytext.gui {
	requires javafx.controls;

    requires easytext.analysis.api;

    exports javamodularity.easytext.gui to javafx.graphics;

    uses javamodularity.easytext.analysis.api.TextAnalyzer;
}