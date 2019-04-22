
package objects;

public class Type<T> {
    
    private T type;
	
	public Type() {}
	
	public Type(T type) {
		this.type = type;
	}

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}
	
}
