grammar Test;

init: test1 | test2 | test3;

test1: (short|long)*;
test2: (SHORT|LONG)*;

short: 'b' 'b' 'b';
long:  'b' 'b' 'b' 'b';

SHORT: 'aaa';
LONG: 'aaaa';

WS: [ \t\r\n]+ -> skip;
