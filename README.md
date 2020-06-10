ResultSetMarshaller
=======================

Shorter way to copy the output result set into the Java beans

<p>Define factory class object<br/>
<pre><code>
//filled result set java.sql.ResultSet 
resultSet; 
UnmarshallerFactory factory = new UnmarshallerFactory();
</code></pre>
Get model type object from factory by passing resultSet as a parameter
<pre><code>
ModelType mType=factory.getModelType(resultSet);
</code></pre>

if result will be a single row then instantiate the Model class 
<pre><code>
Model model=mtype.getModel();
String jsonString=model.getJsonString(); //getting json data from model object 
</code></pre>
or if result may have multiple rows then instantiate the ListModel class
<pre><code>
ListModel listModel=mtype.getListModel();
String jsonString=listModel.getJsonString();  //getting json data from listmodel  object
</code></pre>
Now you have the constructed result and as well as  json string
<pre><code>
YourDomain yd=(YourDomain) model.populateModel(YourDomain.class);
//or
List<Object> listObj=listModel.populateListModel(YourDomain.class);
</code></pre>
</p>


**Computation of information content
```java

public Map<TermId, Double> computeInformationContent(Map<TermId, Collection<TermId>> termLabels) {
        final TermId root = ontology.getRootTermId();//TermId.of("HP:0000118");

        final Map<TermId, Integer> termToFrequency = new HashMap<>();
        for (TermId termId : ontology.getNonObsoleteTermIds()) {
            termToFrequency.put(termId, 0);
        }
        for (Entry<TermId, Collection<TermId>> e : termLabels.entrySet()) {
            termToFrequency.put(e.getKey(), e.getValue().size());
        }

        // Compute information content for each TermId
        final int maxFreq = termToFrequency.get(root);
        final Map<TermId, Double> termToInformationContent = caculateInformationContent(maxFreq, termToFrequency);

        // Fix terms with IC of zero, set it to IC of root
        int countIcZero = 0;
        final double dummyIc = -Math.log(1 / (double) maxFreq);

        for (Term t : ontology.getTerms()) {
            if (t.isObsolete()) {
                continue;
            }
            if (!termToFrequency.containsKey(t.getId())) {
                ++countIcZero;
                termToInformationContent.put(t.getId(), dummyIc);
            }
        }

        return termToInformationContent;
    }


    private Map<TermId, Double> caculateInformationContent(double maxFreq, Map<TermId, Integer> termToFrequency) {
        final Map<TermId, Double> termToIc = new HashMap<>();

        for (Entry<TermId, Integer> e : termToFrequency.entrySet()) {
            final double probability = e.getValue() / maxFreq;
            final double informationContent = e.getValue()>0 ? -Math.log(probability) : 0;
            termToIc.put(e.getKey(), informationContent);
        }

        return termToIc;
    }

```

**Computation of most informative common ancestor

```java

public static TermId mostInformativeCommonAncestor(TermId t1, TermId t2, Ontology ontology, Map<TermId, Double> term2ic) {

        if (t1.equals(t2)) return t1;
        Set<TermId> anc1 = ontology.getAncestorTermIds(t1,false);
        if (anc1.contains(t2)) return t2;
        Set<TermId> anc2 = ontology.getAncestorTermIds(t2,false);
        if (anc2.contains(t1)) return t1;
        Sets.SetView<TermId> intersection = Sets.intersection(anc1,anc2);
        TermId mica = null;
        double maxIC = -1.0; // information content
        Deque<TermId> stack = new ArrayDeque<>();
        stack.push(t1);
        while (! stack.isEmpty()) {
            TermId t = stack.pop();
            if (intersection.contains(t)) {
                double ic = term2ic.get(t);
                if (ic>maxIC) {
                    mica=t;
                    maxIC=ic;
                }
            }
            for (TermId p : ontology.getParentTermIds(t)) {
                stack.push(p);
            }
        }
        return mica;
    }

```




