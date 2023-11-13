// Ejecutador.cpp : Este archivo contiene la función "main". La ejecución del programa comienza y termina ahí.
//

#include <filesystem>
#include <array>
#include <iostream>
#include <array>
#include <cstdio>
#include <cstdlib>

void pyTranslate(const std::string& filePath);

int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cerr << "Usage: " << argv[0] << " <file_path>" << std::endl;
        return 1; // Return non-zero to indicate an error
    }

    std::string filePath = argv[1];
    pyTranslate(filePath);

    return 0;
}

void pyTranslate(const std::string& filePath) {
    std::filesystem::path currentPath = std::filesystem::current_path() / "routes\\javaw";
    std::cout << "currentPath: " << currentPath << std::endl;
    std::cout << "archivo a traducir: " << filePath << std::endl;

    if (std::filesystem::exists(currentPath)) {
        std::string javaClass = "pyTranslator";
        std::string command_path = currentPath.string();
        std::cout << "filePath: " << filePath << std::endl;
        std::string command = "cd \"" + command_path + "\\\" & java " + javaClass + " " + filePath;
        std::cout << "command: " << command << std::endl;
        std::array<char, 128> buffer;
        std::string result;

        FILE* pipe = _popen(command.c_str(), "r");
        if (!pipe) {
            std::cerr << "error trying to make thread";
        }

        while (fgets(buffer.data(), 128, pipe) != nullptr) {
            result += buffer.data();
        }

        std::cout << "javaw_return: " << result << std::endl;
        int exitCode = _pclose(pipe);
        std::cout << exitCode << std::endl;

    } else {
        std::cout << "el traductor no se encuentra dentro de la ruta.";
    }
}

