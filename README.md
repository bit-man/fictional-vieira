# fictional-vieira

Project moved to https://gitlab.com/bit-man/fictional-vieira

## Scripting analysis

The idea behind Fictional Vieira is to combine Bash and Java strengths to
ease glue logic maintenance usually created using a mix of Bash and CLI
programs.

Bash strengths are :

* Quick execution
* Fast prototyping
* Command execution
* Conditional command execution (&&)
* Pipelines (|)
* Redirection (> and >>)

The weakest link in Bash is that it only knows about numbers and strings
then makes it quite difficult to manage anything beyond those. Arrays are
quite useful but they are number and string based also, and its syntax 
they became cryptic beyond the basic access to it members :

* single element : ${array[key]}
* associative array keys : ${!array[@]}
* array all elements : ${!array[*]}
* number of elements : ${#array}

I'm not a big fan of Bash control structures. Being them very useful let's
agree that its syntax is a bit confusing for the untrained eye; the
problem is not 'if', 'for', 'case' or 'while' syntax but the expressions
can be quite complex and difficult to figure out its real job

Java strengths fit in the holes left by Bash weaknesses and vice versa 
(Java is Object Oriented, static typed, verbose and not so good for fast
 prototyping and speed execution ) but also has some other characteristics
that make it very useful for code maintenance :

* solid TDD tools and culture
* modularization
* parallelism
* lots of code to access commercial and Open Source applications

Based on this brief analysis lets Java create Bash scripts that contain
only code using the strengths described previously, no arrays and use 
control structures when needed for runtime decisions only

## Obtaining snapshots

If interested in the SNAPSHOT versions add it to your project POM repository


    <repositories>
        <repository>
            <id>fictional-vieira</id>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>

## ToDo

* Create DEBUG option

