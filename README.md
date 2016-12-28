RandomPattern
=============

Library to generate a random pattern from which a given String is guaranteed to match.

Example:

```"Hello World!"```

may produce:

```H[c-f]l{1,2}\w\sWo[^ras2]l*d```

#Usage:
```
String PatternGenerator.random(CharRange charRange, String string);
```
or using an infinite Stream:
```
Stream<String> PatternGenerator.stream(CharRange charRange, String string);
```

#Benchmark
<img src='https://raw.github.com/gmtuca/RandomPattern/master/images/benchmark_small.png' width='600px'> <br />
<img src='https://raw.github.com/gmtuca/RandomPattern/master/images/benchmark_large.png' width='600px'> <br />

#Samples

Here are examples of 100 elements of the stream given the input CharRange.LATIN_PRINTABLE, "Hello World!"

```
\D+\w{2,6}\s\D{1,2}[h-x]+[\[-k]\D
[^3 O\*]+[e-v]{1,} [^E<]*!
H[^\|Up]l*o*\D{0,}\D+
H[^s]*?\w{2}\D{1,5}[^Xy]\D{3,}
H\S{2}[^\(]{8,9}
[^16=]*
\D*\wl\w\W*W[h-q][o-z]\w*[^\*]{2}
H\Dl*o\D+
\S{4}[m-u] [T-Y]o\S*\D{2,}
He[i-s]{2}o[ -"]W\D{2,}\Dd[ -#]
\D\S+\s\S{3,5}\w\S\W
[A-J]e[^p%]*[^U~/]\w*[^CB]
\D{3,7}o?\D{5,6}d!
\S* \D*
[A-I]e?l*\D*\S\D*
H[^9u!]+[^BZ]?\D{2,}\D{3}
[^\\#]{0,3}\S+[^I;\(]\D{3}\w{2}\W?
\S[^Q8]{4,}\D+\S+
\S{0,2}\S{2,}[^\.gi][P-\]][m-v]{2}\w*[ -\&]
H\w+\w{0,2} \w{4,}[^TL']
H\w+\D\S+\S*
\w{3}l\w\s[T-_]\wr\D+
\S{0,}\S{3,}\D*\w+\D{2}[ -\+]
\D+\D{4,}\S{4,}
\w*[^U]{7,}d\W
He\Sl\w\s\S{0,4}\w{2,}\W
\D*\S{1,2} \w*[^pT]
[^9B]{3}\D*[^32\[]*
H\S{2,}\D{3,}\S{2,}
\S{5} Wo\w{3}?[^\]]
H\D{1,4}\S[ -\)]\S\wr[^k]{3}
H\w{4,4} \Do\S{3,4}
[^"_]{2}\D{3,3}[^\^]{7,10}
[^;/D]{3,}[ -\$][V-`]\w*[^M]{1,}
\w[^E#5]+
[C-I]?[\\-g]\D{3,}\w{4}!
[D-P]\D[^\.]{1,}?[k-p][^bdK]{1,}o[^%pz]+[^\(2]
\w{4}[^`]+[i-m]\S*
H[^:<]+[^gZ]+\s\D{4,}
\S[^9\)][e-p]\D{2,}[ -!]\w{0,}\D\D*
\D{3,6}\D*\s\D\S\S{4}
H\S{2,4}[^y\{/]*\D*[ -\(]
\w[a-k]\w\w{2}[ -\&]\D{4,}
H?\S[^/g]{3}\W\D+\S{2,4}[^D]!
H\D\w{3,6}\D[^Z]{6}
\S{1,3}\w*[ -\&][^b=]{5}[^\('G]
\w[^w]*\w{3,5}?[ -#]\D\S*
[^~>f>]*[^A]+\S{0,2}[o-v]\D{2,}!
H[\[-f][^N/]lo\D*
\S{1,}[^V9;]{4,6}o\w{3}\S
[G-K]e\D\S{2} W[^\-]r\D*
[@-M]?[^da]\S{2,6}\s[Q-`]\w*!
\w*\w\D+o[^~o]?[^t~ ]{1,}
\S+\D{2,}[^Y]\D{4}[^a]+
[^x]+[^l\)1a]{3,}l\S{2}
\S[^\$6]*
[^2\- ]{1,5}\S{1,}\W[V-`]\w[^\[RG]{3,}
\w*[^0\(]{4,}?\S{4,5}\W
H\w\S{2,}\D[^qI]+[e-v][^pQg][^h#i]
[^nXp][^ML]+[h-y]\DW\w\w{2,}\D
He\D*[^KD]*\D+
[D-O]\w{1,4}o \D{5,6}
[^Nl]e\S{3,} \w{2,}\w{2,3}\W
\w{3,}? [T-X]\D+!
[^mf\|m]{7}\w*l[b-i]\W
\S{2,7}[e-u]\WWo[^kZ][h-o]*[^@'%]{0,}
[^\(]+[i-r][h-r]{2} \S*?[^s\{o][\\-g][ -\(]
H\S+[ -']\w\S\w{1,3}\S
[\?-P]\D+l[b-f][^\|u]
\D+\D*[^\*]W\S\D{2,}[_-e]!
\w{5,}[ -\(]?\S*[^ ]{2,6}
\D{0,2}[^_][h-m]\S\D{4}[d-p]+\W
\S*[ -\)]\w+\S{2,3}[ -\)]
[G-Q]\S{3,}\D? [U-\]][i-s]{2,}?\w[ -\+]
H\S+\W\D\S{4,7}
[^0#w\|]\S{2}?[^A]?o [^EJ~]{1,}r\S{3,}
He\S+\W\D{0,2}r\D+[ -\*]
\w{5,}\s*\S[j-u]{2,}?[`-k]*\S
\w{2,5}\w{1,3}\s\S+\D[^\+h]\w\W
[F-I][c-o]{4,} [V-Y][f-y]{3}[\]-f]?\D
\S\S*[^\-<x]*\D*[d-t]d\W
[@-N]\D*[g-t]{2}[ -#][^M]{5,8}[^j"\^]
[^'\&R]{4}\w\s\D{1,}\D+[^"8]
\S{4,5}\w[ -#][T-\^]\w*[^\|R]+\W
\D{3,7}\W\D{2,4}\D{1,5}\W
H[_-j]l*o[ -#]\So[^U80x]{4,}
\w{4}\S\W\S+\D{3}\S
[^i_r]*\S\W*\w*\S{1,}
[F-Q]e\D{3}\D\w{1,2}r[k-m]\w\D
H*\D{3}\w\W\S\S{2,5}?[^w7f][\^-n][^pX]
\D{2,}[n-q]\W\w+\w*!
\w*\w[^Am]W\Drl[^4312]{2}
\D\S+\s[U-\\]*\D\w*[ -\*]
\D{3,4}\w\w\D?W?\S{5}
\D*\S[ -#][^7T]{3}\w[\^-e]?[^n\\q]
\w*\w*[^aLH]{3}[^dK]{1,}?d!
\w*[g-t]\D{3}\D\D*\S?
\D*[^9]\D[c-s]{2,}[ -!]\w{2,5}\w[_-k][^\]]
H\S[^W`]{3,} \D+\D\S
\D{1,}[^q\\\\]{1,}\W\D*\w[^%_gF]
```
