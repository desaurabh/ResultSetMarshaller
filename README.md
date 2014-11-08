result-set-unmarshaller
=======================

Shorter way to copy the output result set into the Java beans

<p>Define factory class object<br/>
<pre><code>
//filled result set java.sql.ResultSet 
resultSet; 
UnmarshallerFactory factory = new UnmarshallerFactory();
</code></pre>
Get model type object from factory by giving resultSet as a parameter
<pre><code>
ModelType mType=factory.getModelType(resultSet);
</code></pre>

if result would be a single row instantiate the Model class 
<pre><code>
Model model=mtype.getModel();
</code></pre>
else result may contain multiple rows 
<pre><code>
ListModel listModel=mtype.getListModel();
</code></pre>
Now you have the constructed result as well as  json string
<pre><code>
YourDomain yd=(YourDomain) model.populateModel(YourDomain.class);
//or
List<Object> listObj=listModel.populateListModel(YourDomain.class);
</code></pre>
</p>





