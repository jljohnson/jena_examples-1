package default_pack;

import java.util.List;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

public class Temperature extends FunctionBase {

    public static final double fahrenheitToCelsiusConstant = 5.0 / 9.0 ;
    public static final double rankineToCelsiusConstant = 5.0 / 9.0 ;
    
    public Temperature() { 
        super() ; 
    }

    @Override 
    public void checkBuild(String uri, ExprList args) {
        if ( args.size() != 1 ) 
            throw new QueryBuildException("Function '"+Lib.className(this)+"' takes one argument") ;
    }

    @Override
    public NodeValue exec(List<NodeValue> args) {
        if ( args.size() > 1 )
            throw new ExprEvalException("replace: Wrong number of arguments: "+args.size()+" : [wanted 1]") ;
        
        NodeValue v1 = args.get(0) ;
        
        return convert(v1) ;
    }
    
    private static NodeValue convert(NodeValue nv)
    {
        if ( nv.getNode().getLiteralDatatype() instanceof TemperatureFahrenheit ) {
            return NodeValue.makeDouble((nv.getDouble() - 32.0) * fahrenheitToCelsiusConstant);
        } else if ( nv.getNode().getLiteralDatatype() instanceof TemperatureKelvin ) {
            return NodeValue.makeDouble(nv.getDouble() - 273.15);
        } else if ( nv.getNode().getLiteralDatatype() instanceof TemperatureRankine ) {
            return NodeValue.makeDouble((nv.getDouble() - 491.67) * rankineToCelsiusConstant);
        } else {
            return nv ;                
        }
    }
        
}