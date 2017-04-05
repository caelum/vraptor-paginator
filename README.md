# VRaptor-paginator

## Installing

Add to your pom:

```xml
  <dependency>
  	<groupId>br.com.caelum.vraptor</groupId>
  	<artifactId>vraptor-paginator</artifactId>
  	<version>4.0.0-SNAPSHOT</version>
  </dependency>
  
```

## Example with JPA

```java
  @Inject
  private JPAPaginatedQuery query;

  @Get("/{page.number}")
  public void home(Page page) {
    //this code could be in your DAO
  	Paginator<Music> paginator = query
  			.jpql("select m from Music m left join m.musicOwners mo where mo.owner=:user")
  			.setParameter("user", user).paginate(page);

  	result.include("paginator", paginator);
  }
  
```

## Example with Hibernate

``` java
    @Inject
    private HibernatePager query;
    @Inject
    private Session session;

    @Get("/{page.number}")
    public void home(Page page) {
      //this code could be in your DAO
    	Paginator<Music> paginator = query
    			.paginate(session.createQuery("select m from Music m left join m.musicOwners mo where mo.owner=:user")
    			.setParameter("user", user),page);

    	result.include("paginator", paginator);
    }

```

## Usage of Paginator in your view

``` java
  <paginator:roller pages="${paginator.pages}"/>
```

This tag just encapsulate a simple foreach. Take a look.

```
    <c:forEach var="page" items="${pages}">
    	<li class="${page.classes}">
    		<c:if test="${!page.hasLink() }">${page.content }</c:if>
    		<c:if test="${page.hasLink() }">
    			<a href="./${page.content}">${page.content }</a>
    		</c:if>
    	</li>
    </c:forEach>
    
```
It is not complicated, if you need to implement your version of a pagination. The `getClasses` method is responsible to
return a css class that can be applied to the `<li>` element. VRaptor-paginator has a three different types of
a <a href="https://github.com/caelum/vraptor-paginator/blob/master/src/main/java/br/com/caelum/vraptor/paginator/view/PageDefinition.java">
PageDefinition</a>. Let's see the `CurrentPage` implementation:

``` java
    public class CurrentPage implements PageDefinition {

    	private final String content;

    	public CurrentPage(Integer next) {
    		this.content = "" + next;
    	}

    	@Override
    	public String getContent() {
    		return content;
    	}

    	@Override
    	public String getClasses() {
    		return "pagination-page pagination-current";
    	}

    	@Override
    	public boolean hasLink() {
    		return false;
    	}

```

You can take a look in the other two classes, **LinkedPageDefinition** and **ThreeDotsDefinition**.
