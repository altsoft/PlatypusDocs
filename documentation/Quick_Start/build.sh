cd en-US
asciidoc -b html5 -a icons -a toc2 -a toclevels=3 --theme altsoft Quick_Start.asciidoc
a2x -fpdf --fop --verbose -dbook -v Quick_Start.asciidoc
mv Quick_Start.html ../../../build/Quick_Start/index.html 
mv Quick_Start.pdf ../../../build/Quick_Start/Quick_Start-en-US.pdf 
cp -r images ../../../build/Quick_Start/

cd ../ru-RU
asciidoc -b html5 -a icons -a toc2 -a toclevels=3 --theme altsoft Quick_Start.asciidoc
a2x -fpdf --fop --verbose -dbook -v Quick_Start.asciidoc
mv Quick_Start.html ../../../build/Quick_Start_RU/index.html 
mv Quick_Start.pdf ../../../build/Quick_Start_RU/Quick_Start-ru-RU.pdf 
cp -r images ../../../build/Quick_Start_RU/
