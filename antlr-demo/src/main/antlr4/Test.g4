grammar Test;

init: test1 | test2;

test1: (small|longg)*;
test2: (SMALL|LONGG)*;

small: 'b' 'b' 'b';
longg:  'b' 'b' 'b' 'b';

SMALL: 'aaa';
LONGG: 'aaaa';

WS: [ \t\r\n]+ -> skip;
