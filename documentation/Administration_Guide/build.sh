cd en-US
asciidoc -b html5 -a icons -a toc2 -a toclevels=3 --theme altsoft Administration_Guide.asciidoc
a2x -fpdf --fop --verbose -dbook -v Administration_Guide.asciidoc
mv Administration_Guide.html ../../../build/Administration_Guide/index.html 
mv Administration_Guide.pdf ../../../build/Administration_Guide/Administration_Guide.pdf 
cp -r images ../../../build/Administration_Guide/
