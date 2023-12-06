Open Project folder in VS code, Open Terminal and enter the following command to run the project
Command-> dotnet run --launch-profile https
URL -> https://localhost:7101/swagger/index.html

Dependecies
1. <PackageReference Include="Microsoft.AspNetCore.OpenApi"/>
2. <PackageReference Include="Microsoft.EntityFrameworkCore"/>
3. <PackageReference Include="Microsoft.EntityFrameworkCore.Design">
	    <IncludeAssets>runtime; build; native; contentfiles; analyzers; buildtransitive</IncludeAssets>
   <PrivateAssets>all</PrivateAssets>
   </PackageReference>
4. <PackageReference Include="Microsoft.EntityFrameworkCore.SQLite"/>
5. <PackageReference Include="Microsoft.EntityFrameworkCore.SqlServer"/>
6.  <PackageReference Include="Microsoft.EntityFrameworkCore.Tools">
      <IncludeAssets>runtime; build; native; contentfiles; analyzers; buildtransitive</IncludeAssets>
      <PrivateAssets>all</PrivateAssets>
    </PackageReference>
7. <PackageReference Include="Microsoft.VisualStudio.Web.CodeGeneration.Design"/>
8. <PackageReference Include="Swashbuckle.AspNetCore"/>