package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Random;

public class MyHashMap<K, V> {

  private static int numberOfNodes = 0;
  private Node[] hashTable;

  public MyHashMap() {
    this.hashTable = new MyHashMap.Node[16];
  }

  public int size() {
    numberOfNodes = 0;
    for (Node node : this.hashTable) {
      if (node != null) {
        numberOfNodes++;
        calcAmountLinkedNodes(node);
      }
    }
    return numberOfNodes;
  }

  public V get(K key) {
    for (Node node : this.hashTable) {
      if (node != null && node.getKey().equals(key)) {
        return node.getValue();
      } else if (node != null) {
        V nodeByKey = findNodeByKey(node, key);
        if(nodeByKey != null){
          return nodeByKey;
        }
      }
    }
    return null;
  }

  public Node put(K key, V value) {
    Node node = new Node();
    int bucket = node.hashCode();
    node.setValue(value);
    node.setKey(key);
    if(this.hashTable[bucket] == null){
      this.hashTable[bucket] = node;
      return node;
    }else{
      Node emptyLinkNode = findEmptyLinkNode(hashTable[bucket]);
      emptyLinkNode.setNext(node);
      return emptyLinkNode;
    }
  }

  public Node remove(K key) {
    for (int i = 0; i < this.hashTable.length; i++) {
      Node nodeToRemove = hashTable[i];
      if(isFirstAndLast(nodeToRemove, key)){
        hashTable[i] = null;
        return nodeToRemove;
      }else if(isFirstAndNotLast(nodeToRemove, key)){
        hashTable[i] = nodeToRemove.getNext();
        return nodeToRemove;
      } else if(nodeToRemove != null && nodeToRemove.getNext() != null){
        Node node = deleteNodeByKey(hashTable[i], hashTable[i].getNext(), key);
        if(node != null) {
          return node;
        }
      }
    }
    return null;
  }

  public void clear() {
    for (int i = 0; i < this.hashTable.length; i++) {
      hashTable[i] = null;
    }
  }

  private Node findEmptyLinkNode(Node node){
    if(node.getNext() == null){
      return node;
    }
    return findEmptyLinkNode(node.getNext());
  }

  private void calcAmountLinkedNodes(Node node){
    if(node.getNext() != null){
      numberOfNodes++;
      calcAmountLinkedNodes(node.getNext());
    }
  }

  private Node deleteNodeByKey(Node previousNode, Node currentNode, K key){
    if(currentNode.getKey().equals(key) && currentNode.getNext() != null){
      previousNode.setNext(currentNode.getNext());
      return currentNode;
    }else if(currentNode.getKey().equals(key) && currentNode.getNext() == null){
      previousNode.setNext(null);
      return currentNode;
    }else if(currentNode.getNext() != null){
      deleteNodeByKey(currentNode, currentNode.getNext(), key);
    }
    return null;
  }

  private V findNodeByKey(Node node, K key){
    if(node.getKey().equals(key)){
      return node.getValue();
    }
    if(node.getNext() != null) {
      return findNodeByKey(node.getNext(), key);
    }
    return null;
  }

  private boolean isFirstAndLast(Node node, K key){
    return node != null && node.getKey().equals(key) && node.getNext() == null;
  }

  private boolean isFirstAndNotLast(Node node, K key){
    return node != null && node.getKey().equals(key);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public class Node {
    private K key;
    private V value;
    private Node next;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return Objects.equals(getKey(), node.getKey()) && Objects.equals(getValue(), node.getValue()) && Objects.equals(getNext(), node.getNext());
    }

    @Override
    public int hashCode() {
      Random random = new Random();
      return random.nextInt(16);
    }

  }

}
