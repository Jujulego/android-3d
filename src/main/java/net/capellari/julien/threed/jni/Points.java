package net.capellari.julien.threed.jni;

import net.capellari.julien.threed.Point2i;
import org.jetbrains.annotations.NotNull;

public class Points {
    // Constructeur
    private Points() {}

    // Méthodes privées
    private native static int getPointCoord_i2(long handle, int i);

    // Méthodes publiques
    public native static long createPoint_i2(int x, int y);
    public static int getCoord(@NotNull Point2i pt, int i) { return getPointCoord_i2(pt.getNativeHandle(), i); }
}
