{
  description = "Kotlin Advent of Code Development Environment";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
      in
      {
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            # Kotlin and JVM
            kotlin
            jdk21
            gradle
            
            # Build and development tools
            git
            curl
            wget
            
            # Optional: for running scripts
            bash
          ];

          shellHook = ''
            echo "🎄 Welcome to Advent of Code 2025 - Kotlin Edition!"
            echo "Available tools:"
            echo "  - kotlin: Kotlin compiler"
            echo "  - gradle: Build tool"
            echo "  - jdk: Java Development Kit (v21)"
            echo ""
            echo "Quick start:"
            echo "  kotlinc -version  # Check Kotlin version"
            echo "  gradle --version  # Check Gradle version"
          '';
        };
      }
    );
}
