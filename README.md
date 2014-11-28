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
</code></pre>
or if result may have multiple rows then instantiate the ListModel class
<pre><code>
ListModel listModel=mtype.getListModel();
</code></pre>
Now you have the constructed result and as well as  json string
<pre><code>
YourDomain yd=(YourDomain) model.populateModel(YourDomain.class);
//or
List<Object> listObj=listModel.populateListModel(YourDomain.class);
</code></pre>
Accessing json string from model object
<code><pre>
String jsonString=model.getJsonString();
</code></pre>
</p>





