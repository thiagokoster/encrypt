let 
	pkgs = import <nixpkgs> {};
in
	pkgs.mkShell {
		packages = [
			pkgs.openjdk21  # Java 21 (used in your project)
    		pkgs.maven
		];
		env = {
			JAVA_HOME = "${pkgs.openjdk21}";
		};
	}

