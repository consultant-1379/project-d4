# ----- Imports ----- #

import csv
from pydriller import Repository
from pydriller.metrics.process.change_set import ChangeSet
from pydriller.metrics.process.code_churn import CodeChurn
from pydriller.metrics.process.contributors_count import ContributorsCount
from pydriller.metrics.process.contributors_experience import ContributorsExperience
from pydriller.metrics.process.hunks_count import HunksCount

# ----- Setup ----- #
# Path to Git repository being used
# TODO: Enter repository path
repo = ""

# Finds the dates of all commits to be used to set boundaries using indexes
dates = []
repoName = ""
# Loop through all commits adding all dates to List
for commit in Repository(repo).traverse_commits():
    dates.append(commit.author_date)
    repoName = commit.project_name
print("Repository: '{}' open. ".format(repoName))
# Set start and end date boundaries for the CSV file
start_date = dates[0]
end_date = dates[len(dates) - 1]

# ----- Metrics ----- #
# Finds the metrics by which to calculate each of the required data fields.
# Prints out completion message when successful
myMetricChangeSet = ChangeSet(path_to_repo=repo, since=start_date, to=end_date)
changeSetMax = myMetricChangeSet.max()
changeSetAvg = myMetricChangeSet.avg()

myMetricContributorsCount = ContributorsCount(path_to_repo=repo, since=start_date, to=end_date)
contributorsCountList = list(myMetricContributorsCount.count().values())
minorContributorsCountList = list(myMetricContributorsCount.count_minor().values())

myMetricExperience = ContributorsExperience(path_to_repo=repo, since=start_date, to=end_date)
contributorsExperienceList = list(myMetricExperience.count().values())

myMetricChurn = CodeChurn(path_to_repo=repo, since=start_date, to=end_date)
codeChurnAvgList = list(myMetricChurn.avg().values())
codeChurnMaxList = list(myMetricChurn.max().values())

myMetricHunksCount = HunksCount(path_to_repo=repo, since=start_date, to=end_date)
hunksCountList = list(myMetricHunksCount.count().values())

print("All Metrics Complete. ")

# ----- Write to CSV file ----- #
# Set Header values for CSV file for database
header = ['commit_hash', 'date', 'committer', 'files_in_commit', 'number_of_commits', 'file_hash', 'repo_name',
          'filename', 'lines_added', 'lines_removed', 'change_set_max', 'change_set_avg', 'contributors_count',
          'minor_contributors_count', 'contributor_experience', 'code_churn_avg', 'code_churn_max', 'hunks_count']

# Open CSV file to be writable
with open('{}.csv'.format(repoName), 'w',
          encoding='UTF8', newline='') as f:
    # Make A DictWriter for the CSV file and set 'header' to be at the top
    writer = csv.DictWriter(f, fieldnames=header)
    writer.writeheader()
    count, count2, numCommits = 0, 0, 0
    rows = []
    for commit in Repository(repo, since=start_date, to=end_date).traverse_commits():
        numCommits += 1
    # Loop through git repository with set date boundaries
    for commit in Repository(repo, since=start_date, to=end_date).traverse_commits():
        # Loop through all modified files in the commits in the repository
        for m in commit.modified_files:
            # Set values for each row matching the 'header' setup
            rows = [
                {'commit_hash': commit.hash,
                 'date': str(commit.author_date.combine(commit.author_date.date(), commit.author_date.time())),
                 'committer': str(commit.committer.name),
                 'files_in_commit': commit.files,
                 'number_of_commits': numCommits,
                 'file_hash': m.__hash__(),
                 'repo_name': commit.project_name,
                 'filename': m.filename,
                 'lines_added': m.added_lines,
                 'lines_removed': m.deleted_lines,
                 'change_set_max': changeSetMax,
                 'change_set_avg': changeSetAvg,
                 'contributors_count': contributorsCountList[count2],
                 'minor_contributors_count': minorContributorsCountList[count2],
                 'contributor_experience': contributorsExperienceList[count2],
                 'code_churn_avg': codeChurnAvgList[count2],
                 'code_churn_max': codeChurnMaxList[count2],
                 'hunks_count': hunksCountList[count2]
                 }
            ]

            # Write the row to the file whenever it is complete
            writer.writerows(rows)
        # Increment count to keep track of commits
        count += 1
        # Increment count2 until it reaches list length
        if count2 == len(contributorsExperienceList) - 1:
            count2 = 0
        else:
            count2 += 1

# Print total time taken and completion message
print("File {}.csv wrote to.".format(repoName))
