import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Region implements Serializable, Cloneable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6801870320013466345L;
	protected LeftRightBound left;
    protected TopBottomBound top;
    protected LeftRightBound right;
    protected TopBottomBound bottom;
        
    public Region() { super();
    	left = new LeftRightBound();
    	top = new TopBottomBound();
    	right = new LeftRightBound();
    	bottom = new TopBottomBound();
    }

    // Left, Top, Right, Bottom
    Region(LeftRightBound ell, TopBottomBound t, LeftRightBound r, TopBottomBound b)
    {
    	left = ell;
    	top = t;
    	right = r;
    	bottom = b;
    }

    public Region(Region that)
    {
    	this.left = that.left;
    	this.right = that.right;
    	this.top = that.top;
    	this.bottom = that.bottom;
    }

    public void instantiateLeft(Point pt)
    {
        left = new LeftRightBound(new PointBound(pt));
    }
    
    public void instantiateLeft(LineSegment line)
    {
        left = new LeftRightBound(new LineSegment(line));
    }

    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof Region)) return false;

    	Region that = (Region)obj;
    	
    	if (!this.left.equals(that.left)) return false;

        if (!this.right.equals(that.right)) return false;

        if (!this.top.equals(that.top)) return false;

        if (!this.bottom.equals(that.bottom)) return false;

    	return true;
    }

    public String toString()
    {
        String s = "";

        s += "Left: " + this.left + "\n";
        s += "Top: " + this.top + "\n";
        s += "Right: " + this.right + "\n";
        s += "Bottom: " + this.bottom + "\n\n";

        return s;
    }
    
    public Region clone() {
    	Region obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = (Region) in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
}
