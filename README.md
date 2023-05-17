

<body class="stackedit">
  <div class="stackedit__html"><h1 id="music-archive-documentation">Music Archive</h1>
<h2 id="overview">Overview</h2>
<p>Music Archive is a Java-based web service that enables users to listen to and upload songs in MP3 format. It uses the Spring Boot framework and integrates with AWS S3 for file storage and AWS CloudFront for content delivery using a CDN. The application also uses MongoDB to store and retrieve labels through a REST API. To run the project locally, you need to provide your own environment variables.</p>
<h2 id="technologies-used">Technologies Used</h2>
<ul>
<li>Java 11</li>
<li>Spring Boot</li>
<li>AWS S3</li>
<li>AWS CloudFront</li>
<li>MongoDB</li>
</ul>
<h2 id="requirements">Requirements</h2>
<p>To run the Music Archive project locally, ensure that you have the following dependencies installed:</p>
<ul>
<li>JDK 11</li>
<li>Maven</li>
<li>MongoDB</li>
<li>AWS account with access to S3 and CloudFront</li>
</ul>
<h2 id="setup-instructions">Setup Instructions</h2>
<p>Follow these steps to set up the Music Archive project on your local machine:</p>
<ol>
<li>
<p>Clone the Music Archive project repository from GitHub.</p>
</li>
<li>
<p>Navigate to the project directory in your terminal or command prompt.</p>
</li>
<li>
<p>Rename the <code>.env.example</code> file to <code>.env</code>.</p>
</li>
<li>
<p>Open the <code>.env</code> file and update the environment variables with your specific values. Configure the following variables:</p>
<ul>
<li><code>ACCESS_KEY</code>: Your AWS access key ID.</li>
<li><code>SECRET_KEY</code>: Your AWS secret access key.</li>
<li><code>REGION</code>: The AWS region where your S3 bucket and CloudFront distribution are located.</li>
<li><code>BUCKET_NAME</code>: The name of your S3 bucket for storing songs.</li>
<li><code>CLOUDFRONT_URL</code>: The URL of your CloudFront distribution.</li>
</ul>
</li>
<li>
<p>Save and close the <code>.env</code> file.</p>
</li>
<li>
  <p>Open <code>application.properties</code> and change <code>&ltYOUR MONGODB URI&gt</code> with your MongoDB URI</p>
</li>
<li>  
<p>Save and close the <code>application.properties</code> file.</p>
</li>
<li>
<p>Run the following command in your terminal or command prompt to build and run the Music Archive application:</p>
<p><code>mvn spring-boot:run</code></p>
</li>
<li>
<p>The Music Archive service should now be running locally on your machine.</p>
</li>
</ol>
<h2 id="usage">Usage</h2>
<p>Once the Music Archive service is up and running, you can interact with it using the following endpoints:</p>
<ul>
<li><code>GET /songs</code>: Retrieve a list of all songs in the archive.</li>
<li><code>GET /songs/{id}</code>: Retrieve a specific song by its ID.</li>
<li><code>POST /songs</code>: Upload a new song to the archive. Include the MP3 file as part of the request.</li>
<li><code>DELETE /songs/{id}</code>: Delete a song from the archive by its ID.</li>
</ul>
<p>Note that for endpoints involving file upload/download, such as <code>POST /songs</code>, the file should be sent as a multipart/form-data request.</p>
<h2 id="deploying-to-aws-elastic-beanstalk">Deploying to AWS Elastic Beanstalk</h2>
<p>To deploy the Music Archive service to AWS using Elastic Beanstalk, follow these steps:</p>
<ol>
<li>
<p>Create an S3 bucket to store the songs.</p>
</li>
<li>
<p>Configure the bucket to allow public read access to the objects.</p>
</li>
<li>
<p>Upload the Music Archive JAR file to the S3 bucket.</p>
</li>
<li>
<p>Log in to the AWS Management Console and navigate to the Elastic Beanstalk service.</p>
</li>
<li>
<p>Click on “Create Application” and provide a name for your application.</p>
</li>
<li>
<p>Choose the desired platform (Java) and select “Upload your code” as the source.</p>
</li>
<li>
<p>Upload the Music Archive JAR file from the S3 bucket.</p>
</li>
<li>
<p>Configure the environment settings for your Elastic Beanstalk environment. Set the necessary environment variables, such as <code>AWS_S3_BUCKET_NAME</code> and <code>AWS_CLOUDFRONT_URL</code>, using the values appropriate for your setup.</p>
</li>
<li>
<p>Review the configuration details and click “Create Application.”</p>
</li>
<li>
<p>Elastic Beanstalk will create the necessary resources and deploy the Music Archive application. This process may take a few minutes.</p>
</li>
<li>
<p>Once the deployment is complete, you can access your Music Archive service by navigating to the Elastic Beanstalk environment URL provided in the Elastic Beanstalk dashboard.</p>
</li>
<li>
<p>Test the deployed service by using the provided endpoints and verifying that the functionality works as expected.</p>
</li>
</ol>
</div>
<h2 ">To be continued...</h2>
</body>

</html>
