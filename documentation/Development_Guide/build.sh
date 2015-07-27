cd en-US
asciidoc -b html5 -a icons -a toc2 -a toclevels=3 --theme altsoft Development_Guide.asciidoc
a2x -fpdf --fop --verbose -dbook -v Development_Guide.asciidoc
mv Development_Guide.html ../../../build/Development_Guide/index.html 
mv Development_Guide.pdf ../../../build/Development_Guide/Development_Guide-en-US.pdf 
cp -r images ../../../build/Development_Guide/
