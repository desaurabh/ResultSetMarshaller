result-set-unmarshaller
=======================

Shorter way to copy the output result set into the Java beans

Define factory class object
//filled result set java.sql.ResultSet
resultSet;
UnmarshallerFactory factory = new UnmarshallerFactory();

Get model type object from factory by giving resultSet as a parameter

ModelType mType=factory.getModelType(resultSet);

if result would be a single row instantiate the Model class 

Model model=mtype.getModel();

else result would contain multiple rows 

ListModel listModel=mtype.getListModel();

Now you have the constructed result as well as  json 

YourDomain yd=(YourDomain) model.populateModel(YourDomain.class);
or
List<Object> listObj=listModel.populateListModel(YourDomain.class);






