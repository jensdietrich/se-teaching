// simple CSV grammar with additional comments
grammar CSVPlus;

csv : (row | COMMENT) * ;
row : cell (',' cell)* '\r'? '\n' ;   // windows uses \r\n for linebreaks, unix just \n
cell : TEXT ;
COMMENT : '#' TEXT '\r'? '\n' ;
TEXT   : ~[#,\n\r]+ ;   // everything that is *not* a linebreak or comma used as cell separator or comment indicator
