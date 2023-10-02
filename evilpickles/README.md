# Unsafe Deserialization in Java -- DOS

Examples from [Jens Dietrich, Kamil Jezek, Shawn Rasheed, Amjed Tahir, and Alex Potanin. "Evil pickles: DoS attacks based on object-graph engineering." ECOOP'2017](https://drops.dagstuhl.de/opus/volltexte/2017/7260/pdf/LIPIcs-ECOOP-2017-10.pdf)
showing how de-serialising object streams can lead to resource exhaustion.

For similar vulnerabilities in parsers for formats inluding YAML, PDF and SVG see also:
[Rasheed, Shawn, Jens Dietrich, and Amjed Tahir. "Laughter in the wild: A study into dos vulnerabilities in YAML libraries." TrustCom'2019.](https://ieeexplore.ieee.org/iel7/8883860/8887294/08887385.pdf)


