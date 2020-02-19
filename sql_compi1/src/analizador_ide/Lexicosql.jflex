package analizador_ide;
import java_cup.runtime.Symbol;
%%//Separador de area

%class lexicoSql
%cup
%line
%column
%full
%char
%public

/*Identifiers*/
letra = [a-zA-Z]
symbols = [-_@+*#.]
numero = [0-9]
space = [ ]
lineTerminator = [\t,\r]+

// metodos
%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
       // System.out.println("Error-->linea:"+yyline+"\n columna: "+yycolumn+" lexema:"+value+" "+yychar);
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

%}
%%//Separdar de area

<YYINITIAL> {
     "SELECCIONAR"                         {return symbol(sym.SELECT, yytext());}
     "FILTRAR"                             {return symbol(sym.FILTRAR, yytext());}
     "INSERTAR"                            {return symbol(sym.INSERT, yytext());}
     "ACTUALIZAR"                          {return symbol(sym.UPDATE, yytext());}
     "ASIGNAR"                             {return symbol(sym.ASIG, yytext());}
     "ELIMINAR"                            {return symbol(sym.DELETE, yytext());}
     "("                                   {return symbol(sym.PA, yytext());}
     ")"                                   {return symbol(sym.PC, yytext());}
     "."                                   {return symbol(sym.POINT, yytext());}
     "EN"                                  {return symbol(sym.IN, yytext());}
     "VALORES"                             {return symbol(sym.VALUES, yytext());}
      \"                                   {return symbol(sym.COMILLAS, yytext());}
     " "                                   {return symbol(sym.SPACE, "espacio");}
     ","                                   {return symbol(sym.COMA, yytext());}
     ";"                                   {return symbol(sym.END, yytext());}
     "="                                   {return symbol(sym.IGUAL, yytext());}
     ({numero}+)                           {return symbol(sym.NUMBER, yytext());}  
     "AND"                                 {return symbol(sym.Y, yytext());}  
     "OR"                                  {return symbol(sym.O, yytext());}  
     ">"                                   {return symbol(sym.MAYOR, yytext());}  
     "<"                                   {return symbol(sym.MENOR, yytext());}  
     "<="                                  {return symbol(sym.MAI, yytext());}  
     ">="                                  {return symbol(sym.MEI, yytext());}  
     "<>"                                  {return symbol(sym.COM, yytext());}  
     "*"                                   {return symbol(sym.ALL, yytext());}  
    ({letra})({letra}|{numero}|{symbols})* {return symbol(sym.ID,new String(yytext()));}
    {lineTerminator}                        {}  
    "\n"                                    {return symbol(sym.ENTER);} 
     .                                      { return symbol(sym.ERROR,new String(yytext()));
                                              }  
}