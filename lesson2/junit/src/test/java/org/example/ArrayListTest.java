package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayListTest {

  ArrayList<String> arrayList;

  @BeforeEach
  public void setUp(){
    arrayList = new ArrayList<>(new String[16]);
  }

  @Test
  void shouldReturnArraySize16(){
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnlyNulls();
  }

  @Test
  void shouldAddElementToArray(){
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnlyNulls();

    boolean result = arrayList.add("qwerty");

    assertThat(result).isTrue();
    assertThat(arrayList.getArray())
        .isNotNull()
        .contains("qwerty");
  }

  @Test
  void shouldNotAddElementToArrayIfArrayIsFull(){
    fillFullArray();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1");

    boolean result = arrayList.add("qwerty");

    assertThat(result).isFalse();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1");
  }

  @Test
  void shouldNotAddNullElementToArray(){
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnlyNulls();

    boolean result = arrayList.add(null);

    assertThat(result).isFalse();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnlyNulls();
  }

  @Test
  void shouldRemoveSingleElementFromTheEndOfArray(){
    fillArray();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", "3", null);

    boolean result = arrayList.remove("3");

    assertThat(result).isTrue();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", null);
  }

  @Test
  void shouldRemoveSingleElementFromTheMiddleOfArray(){
    fillArray();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", "3", null);

    boolean result = arrayList.remove("2");

    assertThat(result).isTrue();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "3", null)
        .isEqualTo(new String[]{"1", "3", null, null, null, null, null, null, null, null, null, null, null, null, null, null});
  }

  @Test
  void shouldRemoveAllElementsFromTheArray(){
    fillArrayInDifferentPosition();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", "3", null);

    boolean result = arrayList.remove("2");

    assertThat(result).isTrue();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "3", null)
        .isEqualTo(new String[]{"1", "3", null, null, null, null, null, null, null, null, null, null, null, null, null, null});
  }

  @Test
  void shouldNotRemoveElementIfItIsAbsent(){
    fillArray();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", "3", null);

    boolean result = arrayList.remove("4");

    assertThat(result).isFalse();
    assertThat(arrayList.getArray())
        .isNotNull()
        .hasSize(16)
        .containsOnly("1", "2", "3", null);
  }

  private void fillFullArray(){
    arrayList.setArray(new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"});
  }

  private void fillArray(){
    arrayList.setArray(new String[]{"1", "2", "3", null, null, null, null, null, null, null, null, null, null, null, null, null});
  }

  private void fillArrayInDifferentPosition(){
    arrayList.setArray(new String[]{"1", "2", "3", null, null, null, null, null, null, null, null, "2", null, null, null, null});
  }

}
