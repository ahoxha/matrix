package org.hoxha.matrix.domain;

public final class Result {
    private int[][] multiplicationsMatrix;
    private int[][] indicesMatrix;

    public int[][] getMultiplicationsMatrix() {
        return multiplicationsMatrix;
    }

    public void setMultiplicationsMatrix(int[][] multiplicationsMatrix) {
        this.multiplicationsMatrix = multiplicationsMatrix;
    }

    public int[][] getIndicesMatrix() {
        return indicesMatrix;
    }

    public void setIndicesMatrix(int[][] indicesMatrix) {
        this.indicesMatrix = indicesMatrix;
    }
}
