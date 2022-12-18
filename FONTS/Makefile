all: main

main:
	javac -sourcepath src/ -cp src/prop/domain/core/dato/lib/antlr-4.9.2-complete.jar src/prop/Main.java -d ./build
	unzip -n -q src/prop/domain/core/dato/lib/antlr-4.9.2-complete.jar "javax/*" "org/*" -d build/

test: main
	find src/test -type f -name "*.java" | xargs -d '\n' javac -sourcepath src/ -cp src/prop/domain/core/dato/lib/antlr-4.9.2-complete.jar:src/test/prop/core/lib/junit-4.12.jar -d ./build
	unzip -n -q src/test/prop/core/lib/junit-4.12.jar "org/*" -d build/

jar: main
	cd build && find . -type f -name "*.class" | xargs -d '\n' jar -cef prop.Main ../Calculo.jar

clean:
	rm -rf build/
	rm -f Calculo.jar
