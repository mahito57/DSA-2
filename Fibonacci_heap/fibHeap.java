package Graph;

class node {
    node parent;
    node left;
    node right;
    node child;
    int vertex;
    int degree;
    boolean mark;
    int key;

    public node() {
        this.degree = 0;
        this.mark = false;
        this.parent = null;
        this.left = this;
        this.right = this;
        this.child = null;
        this.key = Integer.MAX_VALUE;
    }

    node(int x){
        this();
        this.key=x;
    }
    node(int u,int x) {
        this();
        this.vertex=u;
        this.key = x;
    }

    void set_parent(node x) {
        this.parent = x;
    }

    node get_parent() {
        return this.parent;
    }

    void set_left(node x) {
        this.left = x;
    }

    node get_left() {
        return this.left;
    }

    void set_right(node x) {
        this.right = x;
    }

    node get_right() {
        return this.right;
    }

    void set_child(node x) {
        this.child = x;
    }

    node get_child() {
        return this.child;
    }

    void set_degree(int x) {
        this.degree = x;
    }

    int get_degree() {
        return this.degree;
    }

    void set_mark(boolean m) {
        this.mark = m;
    }

    boolean get_mark() {
        return this.mark;
    }

    void set_key(int x) {
        this.key = x;
    }

    int get_key() {
        return this.key;
    }

    int getVertex(){
        return this.vertex;
    }
}


public class fibHeap {
    node min;
    int n;
    boolean trace;
    node found;

    public boolean get_trace() {
        return trace;
    }

    public void set_trace(boolean t) {
        this.trace = t;
    }

    public static fibHeap create_heap() {
        return new fibHeap();
    }

    fibHeap() {
        min = null;
        n = 0;
        trace = false;
    }

     void insert(node x) {
        if (min == null) {
            min = x;
            x.set_left(min);
            x.set_right(min);
        } else {
            x.set_right(min);
            x.set_left(min.get_left());
            min.get_left().set_right(x);
            min.set_left(x);
            if (x.get_key() < min.get_key())
                min = x;
        }
        n += 1;
    }

    public void insert(int key) {
        insert(new node(key));
    }

    public int find_min() {
        return this.min.get_key();
    }

    public node extract_min() {
        node z = this.min;
        if (z != null) {
            node c = z.get_child();
            node k = c, p;
            if (c != null) {
                do {
                    p = c.get_right();
                    insert(c);
                    c.set_parent(null);
                    c = p;
                } while (c != null && c != k);
            }
            z.get_left().set_right(z.get_right());
            z.get_right().set_left(z.get_left());
            z.set_child(null);
            if (z == z.get_right())
                this.min = null;
            else {
                this.min = z.get_right();
                this.consolidate();
            }
            this.n -= 1;
            return z;
        }
        return null;
    }

    public void consolidate() {
        double phi = (1 + Math.sqrt(5)) / 2;
        int Dofn = (int) (Math.log(this.n) / Math.log(phi));
        node[] A = new node[Dofn + 1];
        for (int i = 0; i <= Dofn; ++i)
            A[i] = null;
        node w = min;
        if (w != null) {
            node check = min;
            do {
                node x = w;
                int d = x.get_degree();
                while (A[d] != null) {
                    node y = A[d];
                    if (x.get_key() > y.get_key()) {
                        node temp = x;
                        x = y;
                        y = temp;
                        w = x;
                    }
                    fib_heap_link(y, x);
                    check = x;
                    A[d] = null;
                    d += 1;
                }
                A[d] = x;
                w = w.get_right();
            } while (w != null && w != check);
            this.min = null;
            for (int i = 0; i <= Dofn; ++i) {
                if (A[i] != null) {
                    insert(A[i]);
                }
            }
        }
    }

    private void fib_heap_link(node y, node x) {
        y.get_left().set_right(y.get_right());
        y.get_right().set_left(y.get_left());

        node p = x.get_child();
        if (p == null) {
            y.set_right(y);
            y.set_left(y);
        } else {
            y.set_right(p);
            y.set_left(p.get_left());
            p.get_left().set_right(y);
            p.set_left(y);
        }
        y.set_parent(x);
        x.set_child(y);
        x.set_degree(x.get_degree() + 1);
        y.set_mark(false);
    }

}
