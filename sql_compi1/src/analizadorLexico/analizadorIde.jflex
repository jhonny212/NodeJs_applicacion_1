package analizadorLexico;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

%%//Separador de area

%class AnalizadorLexicoIDE
%cup
%cupdebug
%line
%column
%full
%char
%public

/*Identifiers*/
letra=[a-zA-Z]
numero=[1-9]
symbols=[_-]
lineTerminator=[\t,\r,\n]+

/* functions */
%{
public static ArrayList<Symbol> errorLexico;
   
     private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
/* regular expressions */

%%//area separator
<YYINITIAL>{
    "</CARPETA>"                            {return symbol(sym.closefolder,yytext());}
    "home"                                  {return symbol(sym.HOME,yytext());}
    "</PROYECTO>"                           {return symbol(sym.closeproject,yytext());}
    "CARPETA"                               {return symbol(sym.folder,yytext());}
    "PROYECTO"                              {return symbol(sym.project,yytext());}
    "ARCHIVO"                               {return symbol(sym.file,yytext());}
    "nombre"                                {return symbol(sym.name,yytext());}
    "ubicacion"                             {return symbol(sym.path,yytext());}
     " "                                    {return symbol(sym.space,yytext());}
    "csv"                                   {return symbol(sym.extension,yytext());}
    ">"                                     {return symbol(sym.mayor,yytext());}
    "<"                                     {return symbol(sym.menor,yytext());}
     \"                                     {return symbol(sym.comillas,yytext());}
    "="                                     {return symbol(sym.equals,yytext());}
    "/"                                     {return symbol(sym.slash,yytext());}
    "."                                     {return symbol(sym.point,yytext());}
    ({letra})({letra}|{numero}|{symbols})*  {return symbol(sym.ID,new String(yytext()));}
    {lineTerminator}                        {}  
     .                                      { 
                                          //  System.out.println("Error-->linea:"+yyline+" columna: "+yycolumn+" lexema:"+yytext()+" "+yychar);
                                            return symbol(sym.ERROR,new String(yytext()));
                                            }  


}