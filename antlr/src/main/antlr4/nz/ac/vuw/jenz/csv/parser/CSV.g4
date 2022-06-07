// simple CSV grammar
grammar CSV;

csv : row* ;
row : cell (',' cell)* '\r'? '\n' ;   // windows uses \r\n for linebreaks, unix just \n
cell : TEXT ;
TEXT   : ~[,\n\r]+ ;   // everything that is *not* a linebreak or comma used as cell separator
